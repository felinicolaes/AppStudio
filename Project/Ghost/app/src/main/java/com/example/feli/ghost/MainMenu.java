package com.example.feli.ghost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * MainMenu Activity starts a screen where the player can choose what to do
 */
public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ImageView logoImg = (ImageView) findViewById(R.id.ghost);
        logoImg.setImageResource(R.drawable.ghost);

        //set the resume-button invisible if no previous games are found
        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        if (settings.getString("name0", "").equals("")) {
            Button resumeButton = (Button) findViewById(R.id.resumeGame);
            resumeButton.setVisibility(View.INVISIBLE);
        }
    }

    /*
     * Create buttons
     */
    public void resumeButton(View view) {
        Intent mainGameIntent = new Intent(this, MainGame.class);
        startActivity(mainGameIntent);
    }

    public void playButton(View view) {
        Intent pickPlayerIntent = new Intent(this, PickPlayer.class);
        startActivity(pickPlayerIntent);
    }

    public void howToButton(View view) {
        Intent howToIntent = new Intent(this, HowTo.class);
        startActivity(howToIntent);
    }

    public void highscoresButton(View view) {
        Intent highscoresIntent = new Intent(this, Highscores.class);
        startActivity(highscoresIntent);
    }

    /*
     * Create options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
