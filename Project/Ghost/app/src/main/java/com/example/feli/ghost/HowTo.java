package com.example.feli.ghost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/**
 * HowTo Activity starts a screen where the player can see the rules of the game
 */
public class HowTo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);

        ImageView howToImg = (ImageView) findViewById(R.id.howTo);
        howToImg.setImageResource(R.drawable.howto);
    }

    /*
     * Create buttons
     */
    public void backToMenuButton(View view) {
        Intent mainMenuIntent = new Intent(HowTo.this, MainMenu.class);
        HowTo.this.startActivity(mainMenuIntent);
    }

    public void playButton(View view) {
        Intent pickPlayerIntent = new Intent(HowTo.this, PickPlayer.class);
        HowTo.this.startActivity(pickPlayerIntent);
    }

    /*
     * Create options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_how_to, menu);
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
