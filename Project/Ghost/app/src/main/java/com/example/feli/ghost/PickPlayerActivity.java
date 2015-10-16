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
 * PickPlayerActivity Activity starts a screen where the player can make new players or go to a screen
 * to choose old ones
 */
public class PickPlayerActivity extends AppCompatActivity {
    private String[] currentPlayers = new String[2];
    private EditText name0, name1;
    private Button player0, old0, player1, old1;
    private TextView chosen0, chosen1;
    private String sourcePath = "dutch.txt";
    private Toast message;
    private SharedPreferences settings;
    private ArrayList<String> allNames = new ArrayList<>();

    /* onCreate: Create all text and buttons and get all information for activity to work.
     */
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

    /* getAllPlayer: Get all previously made players from the shared preferences and add their
     * names to allNames, so this name can be checked for similarity when a new name is entered
     */
    public void getAllPlayers() {
        Map<String, ?> allPrefs = settings.getAll();
        allNames.clear();

        for (Map.Entry<String, ?> entry : allPrefs.entrySet()) {
            if (!entry.getKey().equals("sourcePath") && !entry.getKey().equals("name0") && !entry.getKey().equals("name1") &&
                    !entry.getKey().equals("alreadyGuessed") && !entry.getKey().equals("turn")) {
                allNames.add(entry.getKey().toLowerCase());
            }
        }
    }

    /* makeButtons: Make all necessary buttons so they can be changed in this activity
     */
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

    /* getOldPreferences: Get the old preferences, so that the previously chosen player is showed
     * when returning to this activity after going to the getOldPlayer activity
     */
    public void getOldPreferences() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("name")) {
            makePlayer(extras.getString("name"), extras.getInt("score"), extras.getInt("player"));
            if (extras.getInt("player") == 1) {
                currentPlayers[0] = extras.getString("otherPlayer");
            } else {
                currentPlayers[1] = extras.getString("otherPlayer");
            }
        }
    }

    /* setButtons: If a player has already been chosen, set buttons for choosing old/entering new
     * players invisible and show the name of the chosen player
     */
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

    /* resetButtons: If a player has not already been chosen, set buttons for choosing old/entering
     * new players visible
     */
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

    /* setPreferences: Set all information about the chosen players in the shared preferences
     */
    public void setPreferences() {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("sourcePath", sourcePath);
        editor.putString("name0", currentPlayers[0]);
        editor.putString("name1", currentPlayers[1]);
        editor.putString("alreadyGuessed", "");
        editor.putBoolean("turn", false);
        editor.commit();
    }

    /* makePlayer: Add the chosen player to the shared preferences and save their name
     */
    public void makePlayer(String playerName, int playerScore, int playerID) {
        currentPlayers[playerID] = playerName;
        makeToast("Player is saved");
        setButtons();

        allNames.add(playerName.toLowerCase());

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(playerName, playerScore);
        editor.commit();
    }

    /* makeToast: Make a new toast. If a toast was already showing, cancel this toast
     */
    public void makeToast(String text) {
        if (message != null) {
            message.cancel();
        }
        message = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        message.show();
    }

    /* pickPlayerButton: Check if the entered name is a valid, new name and make a new player
     */
    public void pickPlayerButton(View view) {
        switch (view.getId()) {
            case R.id.player0:
                if (name0.getText().toString().equals("")) {
                    makeToast("Please enter a valid name");
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

    /* getOldPlayerButton: Go to the getOldPlayer activity and remember which player is being
     * chosen
     */
    public void getOldPlayerButton(View view) {
        Intent getOldPlayerIntent = new Intent(this, GetOldPlayerActivity.class);

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

    /* playButton: Check if all set information is legal and set the language. Then save the
     * preferences and go to the mainGame activity
     */
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
            Intent mainGameIntent = new Intent(this, MainGameActivity.class);
            startActivity(mainGameIntent);
        }
    }

    /* resetButton: reset all previously entered information so new players can be added
     */
    public void resetButton(View view) {
        currentPlayers[0] = null;
        currentPlayers[1] = null;
        resetButtons();
        makeToast("Players are reset, please choose or enter new players");
    }

    /* * * Create options menu * * */
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
