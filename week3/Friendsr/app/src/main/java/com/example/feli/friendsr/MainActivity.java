package com.example.feli.friendsr;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int[] yesOrNo = new int[6];
    ImageButton[] button = new ImageButton[6];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button[0] = (ImageButton) findViewById(R.id.chandler);
        button[1] = (ImageButton) findViewById(R.id.joey);
        button[2] = (ImageButton) findViewById(R.id.monica);
        button[3] = (ImageButton) findViewById(R.id.phoebe);
        button[4] = (ImageButton) findViewById(R.id.rachel);
        button[5] = (ImageButton) findViewById(R.id.ross);

        //Get the array with colors
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            yesOrNo = extras.getIntArray("friendArray");
        } else {
            yesOrNo[0] = 2;
            yesOrNo[1] = 2;
            yesOrNo[2] = 2;
            yesOrNo[3] = 2;
            yesOrNo[4] = 2;
            yesOrNo[5] = 2;
        }

        //color the background
        makeBackground(yesOrNo);
    }

    public void makeBackground(int[] yesOrNo) {
        for(int i=0; i<6; i++) {
            if (yesOrNo[i] == 0) {
                button[i].setBackgroundColor(Color.RED);
            } else if (yesOrNo[i] == 1) {
                button[i].setBackgroundColor(Color.GREEN);
            } else {
                button[i].setBackgroundColor(Color.WHITE);
            }
        }
    }

    //send the info to PersonalActivity
    public void buttonHandler(View view) {
        int index;
        switch(view.getId()) {
            case R.id.chandler: index = 0; break;
            case R.id.joey: index = 1; break;
            case R.id.monica: index = 2; break;
            case R.id.phoebe: index = 3; break;
            case R.id.rachel: index = 4; break;
            case R.id.ross: index = 5; break;
            default: index = 99; break;
        }
        Intent i = new Intent(MainActivity.this, PersonalActivity.class);
        i.putExtra("friendIndex", index);
        i.putExtra("friendArray", yesOrNo);
        MainActivity.this.startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
