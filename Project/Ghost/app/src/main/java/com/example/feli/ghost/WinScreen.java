package com.example.feli.ghost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * WinScreen Activity starts a screen where information about the end of the game is showed
 */
public class WinScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);

        ImageView winnerImg = (ImageView) findViewById(R.id.congratulationsImg);
        winnerImg.setImageResource(R.drawable.congratulations);

        TextView winner = (TextView) findViewById(R.id.winner);
        TextView reason = (TextView) findViewById(R.id.reason);

        Bundle extras = getIntent().getExtras();
        winner.setText(extras.getString("winnerName") + " won,");
        reason.setText("because " + extras.getString("endReason"));
    }

    /*
     * Create buttons
     */
    public void scoresButton(View view) {
        Intent highscoresIntent = new Intent(this, Highscores.class);
        startActivity(highscoresIntent);
    }

    /*
     * Create options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_win_screen, menu);
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
