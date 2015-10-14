package com.example.feli.ghost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Map;

/**
 * PickPlayer Activity starts a screen where the player can make new players or go to a screen
 * to choose old ones
 */
public class PickPlayer extends AppCompatActivity {
    private String[] currentPlayers = new String[2];
    private EditText name0, name1;
    private Button player0, old0, player1, old1;
    private TextView chosen0, chosen1;
    private String sourcePath = "dutch.txt";
    private Toast message;
    private SharedPreferences settings;
    private ArrayList<String> allNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_player);

        settings = getSharedPreferences("MyPrefsFile", 0);
        getAllPlayers();
        makeButtons();
        getOldPreferences();
        setButtons();

        ImageView playerImg = (ImageView) findViewById(R.id.players);
        playerImg.setImageResource(R.drawable.players);
    }

    public void getOldPreferences() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            makePlayer(extras.getString("name"), extras.getInt("score"), extras.getInt("player"));
            if (extras.getInt("player") == 1) {
                currentPlayers[0] = extras.getString("otherPlayer");
            } else {
                currentPlayers[1] = extras.getString("otherPlayer");
            }
        }
    }

    public void setPreferences() {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("sourcePath", sourcePath);
        editor.putString("name0", currentPlayers[0]);
        editor.putString("name1", currentPlayers[1]);
        editor.putString("guesses", "");
        editor.putBoolean("turn", false);
        editor.commit();
    }

    public void makeButtons() {
        player0 = (Button) findViewById(R.id.player0);
        old0 = (Button) findViewById(R.id.old0);
        name0 = (EditText) findViewById(R.id.name0);
        chosen0 = (TextView) findViewById(R.id.chosen0);
        player1 = (Button) findViewById(R.id.player1);
        old1 = (Button) findViewById(R.id.old1);
        name1 = (EditText) findViewById(R.id.name1);
        chosen1 = (TextView) findViewById(R.id.chosen1);
    }

    public void setButtons() {
        if (currentPlayers[0] != null) {
            player0.setVisibility(View.INVISIBLE);
            old0.setVisibility(View.INVISIBLE);
            name0.setVisibility(View.INVISIBLE);
            chosen0.setText(currentPlayers[0]);
        }
        if (currentPlayers[1] != null) {
            chosen1.setText(currentPlayers[1]);
            player1.setVisibility(View.INVISIBLE);
            old1.setVisibility(View.INVISIBLE);
            name1.setVisibility(View.INVISIBLE);
        }
    }

    public void resetButtons() {
        player0.setVisibility(View.VISIBLE);
        old0.setVisibility(View.VISIBLE);
        name0.setVisibility(View.VISIBLE);
        chosen0.setText("");
        player1.setVisibility(View.VISIBLE);
        old1.setVisibility(View.VISIBLE);
        name1.setVisibility(View.VISIBLE);
        chosen1.setText("");
    }

    public void makePlayer(String playerName, int playerScore, int playerID) {
        currentPlayers[playerID] = playerName;
        makeToast("Player is saved");
        setButtons();

        allNames.add(playerName.toLowerCase());

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(playerName, playerScore);
        editor.commit();
    }

    public void getAllPlayers() {
        Map<String, ?> allPrefs = settings.getAll();
        allNames.clear();

        for (Map.Entry<String, ?> entry : allPrefs.entrySet()) {
            if (!entry.getKey().equals("sourcePath") && !entry.getKey().equals("name0") && !entry.getKey().equals("name1") &&
                    !entry.getKey().equals("guesses") && !entry.getKey().equals("turn")) {
                allNames.add(entry.getKey().toLowerCase());
            }
        }
    }

    public void makeToast(String text) {
        if (message != null) {
            message.cancel();
        }
        message = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        message.show();
    }

    /*
     * Create buttons
     */
    public void pickPlayerButton(View view) {
        switch (view.getId()) {
            case R.id.player0:
                if (name0.getText().toString().equals("")) {
                    makeToast("Please enter a name");
                } else if (allNames.contains(name0.getText().toString().toLowerCase())) {
                    makeToast("This name is already chosen");
                } else {
                    makePlayer(name0.getText().toString(), 0, 0);
                    break;
                }
            case R.id.player1:
                if (name1.getText().toString().equals("")) {
                    makeToast("Please enter a name");
                } else if (allNames.contains(name1.getText().toString().toLowerCase())) {
                    makeToast("This name is already chosen");
                } else {
                    makePlayer(name1.getText().toString(), 0, 1);
                    break;
                }
        }
    }

    public void getOldPlayerButton(View view) {
        Intent getOldPlayerIntent = new Intent(this, GetOldPlayer.class);

        switch (view.getId()) {
            case R.id.old0:
                getOldPlayerIntent.putExtra("player", 0);
                getOldPlayerIntent.putExtra("otherPlayer", currentPlayers[1]);
                startActivity(getOldPlayerIntent);
                break;
            case R.id.old1:
                getOldPlayerIntent.putExtra("player", 1);
                getOldPlayerIntent.putExtra("otherPlayer", currentPlayers[0]);
                startActivity(getOldPlayerIntent);
                break;
        }
    }

    public void playButton(View view) {
        String namePlayer0 = currentPlayers[0];
        String namePlayer1 = currentPlayers[1];

        if (namePlayer0 == null || namePlayer1 == null || namePlayer0.equals("") || namePlayer1.equals("")) {
            makeToast("Please enter and save player names");
        } else {
            switch (view.getId()) {
                case R.id.dutch:
                    sourcePath = "dutch.txt";
                    break;
                case R.id.english:
                    sourcePath = "english.txt";
                    break;
            }
            setPreferences();
            Intent mainGameIntent = new Intent(this, MainGame.class);
            startActivity(mainGameIntent);
        }
    }

    public void resetButton(View view) {
        currentPlayers[0] = null;
        currentPlayers[1] = null;
        resetButtons();
        makeToast("Players are resetted, please choose or enter new players");
    }

    /*
     * Create options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
