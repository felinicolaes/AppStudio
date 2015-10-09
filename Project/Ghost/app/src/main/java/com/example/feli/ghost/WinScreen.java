package com.example.feli.ghost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class WinScreen extends AppCompatActivity {
    String[] players;
    String sourcePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);

        Bundle extras = getIntent().getExtras();
        String endReason = extras.getString("endReason");
        String winningPlayer = extras.getString("winnerName");
        sourcePath = extras.getString("sourcePath");
        players = extras.getStringArray("playerArray");

        TextView winner = (TextView) findViewById(R.id.winner);
        TextView reason = (TextView) findViewById(R.id.reason);

        winner.setText(winningPlayer + " won,");
        reason.setText("because " + endReason);

        System.out.println("sourcepath winscreen " + sourcePath);
    }

    public void scoresButton(View view) {
        Intent i = new Intent(this, Highscores.class);
        startActivity(i);
    }

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
