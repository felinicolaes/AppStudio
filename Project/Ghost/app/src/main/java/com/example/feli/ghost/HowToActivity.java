package com.example.feli.ghost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/**
 * HowToActivity Activity starts a screen where the player can see the rules of the game
 */
public class HowToActivity extends AppCompatActivity {

    /* onCreate: Create all text and buttons and get all information for activity to work
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);

        ImageView howToImg = (ImageView) findViewById(R.id.howTo);
        howToImg.setImageResource(R.drawable.howto);
    }

    /* backToMenuButton: go to the mainMenu activity
     */
    public void backToMenuButton(View view) {
        Intent mainMenuIntent = new Intent(HowToActivity.this, MainMenuActivity.class);
        HowToActivity.this.startActivity(mainMenuIntent);
    }

    /* playButton: go to the pickPlayer activity
     */
    public void playButton(View view) {
        Intent pickPlayerIntent = new Intent(HowToActivity.this, PickPlayerActivity.class);
        HowToActivity.this.startActivity(pickPlayerIntent);
    }

    /* * * Create options menu * * */
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
