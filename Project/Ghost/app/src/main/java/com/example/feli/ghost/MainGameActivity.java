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
 * Ghost App - Feli Nicolaes, feli.nicolaes@uva.student.nl
 *
 * MainGameActivity Activity starts a screen where the game is played
 */
public class MainGameActivity extends AppCompatActivity {
    private EditText guessEdit;
    private TextView guessesText, playerName;
    private String alreadyGuessed, sourcePath;
    private Game newGame;
    private Boolean turn;
    private String[] currentPlayers = new String[2];

    /* onCreate: Create all text and buttons and get all information for activity to work. Also
     * set up the game.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        guessesText= (TextView) findViewById(R.id.guesses);
        guessEdit = (EditText) findViewById(R.id.guess);
        playerName = (TextView) findViewById(R.id.playerName);

        getOldPreferences();

        Lexicon lexicon = new Lexicon(sourcePath, this.getApplicationContext());
        newGame = new Game(lexicon);
        newGame.setTurn(turn);
        newGame.setAlreadyGuessed(alreadyGuessed);

        updateTurn();
    }

    /* getOldPreferences: Get the old preferences, so a game can be restarted after quitting the
     * app
     */
    public void getOldPreferences() {
        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        sourcePath = settings.getString("sourcePath", "dutch.txt");
        currentPlayers[0] = settings.getString("name0", "Jantjedepantje");
        currentPlayers[1] = settings.getString("name1", "Pietje");
        alreadyGuessed = settings.getString("alreadyGuessed", "");
        turn = settings.getBoolean("turn", false);
        guessesText.setText(alreadyGuessed);
        if (currentPlayers[0].equals("Jantjedepantje")) {
            Toast.makeText(getApplicationContext(), "No previous games found, so example game is started", Toast.LENGTH_LONG).show();
        }
    }

    /* updateTurn: Update all textViews and editTexts so a new guess can be made
     */
    public void updateTurn() {
        int turn = newGame.turn()? 1 : 0;
        String currentPlayer = currentPlayers[turn];
        playerName.setText(currentPlayer);
    }

    /* addButton: add the guessed letter to the string 'alreadyGuessed' if it is a legal guess
     */
    public void addButton(View view) {
        if (guessEdit.getText().toString().length() == 1) {
            oneTurn();
        } else {
            Toast.makeText(getApplicationContext(), "Please only add 1 letter", Toast.LENGTH_SHORT).show();
        }
    }

    /* oneTurn: Update all textViews and editTexts and check if game has not ended yet
     */
    public void oneTurn() {
        newGame.guess(guessEdit.getText().toString());
        alreadyGuessed = alreadyGuessed + guessEdit.getText().toString();
        guessEdit.setText("");
        guessesText.setText(alreadyGuessed);
        if (newGame.ended()) {
            endGame();
        } else {
            updateTurn();
        }
    }

    /* endGame: Get the winner, update their score and go to the winScreen activity
     */
    public void endGame() {
        String winner = currentPlayers[newGame.winner()? 1 : 0];
        String reason = newGame.endReason();
        addScore(winner);
        alreadyGuessed = "";
        Intent winScreenIntent = new Intent(this, WinScreenActivity.class);
        winScreenIntent.putExtra("winnerName", winner);
        winScreenIntent.putExtra("endReason", reason);
        startActivity(winScreenIntent);
    }

    /* addScore: Search for the winner in the shared preferences and update their score
     */
    public void addScore(String winner) {
        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        Map<String, ?> allPrefs = settings.getAll();

        for (Map.Entry<String, ?> entry : allPrefs.entrySet()) {
            if (entry.getKey().equals(winner)) {
                Player p = new Player(entry.getKey(), (Integer) entry.getValue());
                p.addScore(alreadyGuessed.length());
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt(winner, p.getScore());
                editor.commit();
            }
        }
    }

    /* onStop: save the preferences if the game is stopped
     */
    @Override
    protected void onStop(){
        super.onStop();
        savePreferences();
    }

    /* savePreferences: save all necessary preferences for the game to be restarted after quitting
     */
    public void savePreferences() {
        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("sourcePath", sourcePath);
        editor.putString("name0", currentPlayers[0]);
        editor.putString("name1", currentPlayers[1]);
        editor.putString("alreadyGuessed", alreadyGuessed);
        editor.putBoolean("turn", newGame.turn());

        editor.commit();
    }

    /* menuButton: Go to the mainMenu activity
     */
    public void menuButton(View view) {
        Intent mainMenuIntent = new Intent(this, MainMenuActivity.class);
        startActivity(mainMenuIntent);
    }

    /* * *Create options menu * * */
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
