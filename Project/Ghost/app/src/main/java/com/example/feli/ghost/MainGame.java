package com.example.feli.ghost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Map;

/**
 * MainGame Activity starts a screen where the game is played
 */
public class MainGame extends AppCompatActivity {
    private EditText guessString;
    private TextView guessesString, playerName;
    private String guesses, sourcePath;
    private Game newGame;
    private Boolean turn;
    private String[] currentPlayers = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        guessesString = (TextView) findViewById(R.id.guesses);
        guessString = (EditText) findViewById(R.id.guess);
        playerName = (TextView) findViewById(R.id.playerName);

        getOldPreferences();

        Lexicon lexicon = new Lexicon(sourcePath, this.getApplicationContext());
        newGame = new Game(lexicon);
        newGame.setTurn(turn);
        newGame.setGuesses(guesses);

        updateTurn();
    }

    public void getOldPreferences() {
        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        sourcePath = settings.getString("sourcePath", "dutch.txt");
        currentPlayers[0] = settings.getString("name0", "Jantjedepantje");
        currentPlayers[1] = settings.getString("name1", "Pietje");
        guesses = settings.getString("guesses", "");
        turn = settings.getBoolean("turn", false);
        guessesString.setText(guesses);
        if (currentPlayers[0].equals("Jantjedepantje")) {
            Toast.makeText(getApplicationContext(), "No previous games found, so example game is started", Toast.LENGTH_LONG).show();
        }
    }

    public void oneTurn() {
        newGame.guess(guessString.getText().toString());
        guesses = guesses + guessString.getText().toString();
        guessString.setText("");
        guessesString.setText(guesses);
        if (newGame.ended()) {
            endGame();
        } else {
            updateTurn();
        }
    }

    public void updateTurn() {
        int turn = newGame.turn()? 1 : 0;
        String currentPlayer = currentPlayers[turn];
        playerName.setText(currentPlayer);
    }

    public void endGame() {
        String winner = currentPlayers[newGame.winner()? 1 : 0];
        String reason = newGame.endReason();
        addScore(winner);
        guesses = "";
        Intent winScreenIntent = new Intent(this, WinScreen.class);
        winScreenIntent.putExtra("winnerName", winner);
        winScreenIntent.putExtra("endReason", reason);
        startActivity(winScreenIntent);
    }

    public void addScore(String winner) {
        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        Map<String, ?> allPrefs = settings.getAll();

        for (Map.Entry<String, ?> entry : allPrefs.entrySet()) {
            if (entry.getKey().equals(winner)) {
                Player p = new Player(entry.getKey(), (Integer) entry.getValue());
                p.addScore(guesses.length());
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt(winner, p.getScore());
                editor.commit();
            }
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        savePreferences();
    }

    public void savePreferences() {
        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("sourcePath", sourcePath);
        editor.putString("name0", currentPlayers[0]);
        editor.putString("name1", currentPlayers[1]);
        editor.putString("guesses", guesses);
        editor.putBoolean("turn", newGame.turn());

        editor.commit();
    }

    /*
     * Create buttons
     */
    public void addButton(View view) {
        if (guessString.getText().toString().length() == 1) {
            oneTurn();
        } else {
            Toast.makeText(getApplicationContext(), "Please only add 1 letter", Toast.LENGTH_SHORT).show();
        }
    }

    public void menuButton(View view) {
        Intent mainMenuIntent = new Intent(this, MainMenu.class);
        startActivity(mainMenuIntent);
    }

    /*
     * Create options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_game, menu);
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
