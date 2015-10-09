package com.example.feli.ghost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

public class PickPlayer extends AppCompatActivity {
    String[] currentPlayers = new String[2];
    EditText name0, name1;
    String sourcePath = "dutch.txt";
    Toast message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_player);
        name0 = (EditText) findViewById(R.id.name0);
        name1 = (EditText) findViewById(R.id.name1);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            makePlayer(extras.getString("name"), extras.getInt("player"));
        }
    }

    public void pickPlayerButton(View view) {
        switch (view.getId()) {
            case R.id.player0:
                makePlayer(name0.getText().toString(), 0);
                break;
            case R.id.player1:
                makePlayer(name1.getText().toString(), 1);
                break;
        }
    }

    public void oldPlayerButton(View view) {
        Intent i = new Intent(this, GetOldPlayer.class);
        switch (view.getId()) {
            case R.id.old0:
                i.putExtra("player", 0);
                startActivity(i);
                break;
            case R.id.old1:
                i.putExtra("player", 1);
                startActivity(i);
                break;
        }
    }

    public void makePlayer(String playerName, int playerID) {
        currentPlayers[playerID] = playerName;
        makeToast("Player is saved");

        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(playerName, 0);
        editor.commit();
    }

    public void makeToast(String text) {
        if (message != null) {
            message.cancel();
        }
        message = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        message.show();
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
            Intent i = new Intent(this, MainGame.class);
            i.putExtra("sourcePath", sourcePath);
            i.putExtra("playerArray", currentPlayers);
            startActivity(i);
        }
    }

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
