package com.example.feli.friendsr;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalActivity extends AppCompatActivity {
    static int friendIndex;
    int index;
    int[] yesOrNo = new int[6];

    //put information of clicked person on the screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        Bundle extras = getIntent().getExtras();
        index = extras.getInt("friendIndex", -1);
        yesOrNo = extras.getIntArray("friendArray");

        TextView friendFullName = (TextView) findViewById(R.id.friendFullName);
        String[] names = getResources().getStringArray(R.array.friend_full_names);
        friendFullName.setText(names[index]);

        getImage(index);

        TextView friendDetails = (TextView) findViewById(R.id.friendDetails);
        String[] details = getResources().getStringArray(R.array.friend_details);
        friendDetails.setText(details[index]);

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(PersonalActivity.this, MainActivity.class);
                i.putExtra("friendArray", yesOrNo);
                PersonalActivity.this.startActivity(i);
            }
        });

        setPersonalBackground();
    }

    //set background red or green
    public void setPersonalBackground() {
        if (yesOrNo[index] == 0) {
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        } else if (yesOrNo[index] == 1) {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        }

        Button yesButton = (Button) findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                yesOrNo[index] = 1;
            }
        });

        Button noButton = (Button) findViewById(R.id.noButton);
        noButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getWindow().getDecorView().setBackgroundColor(Color.RED);
                yesOrNo[index] = 0;
            }
        });
    }

    //get image of clicked person
    public void getImage(int index) {
        ImageView img= (ImageView) findViewById(R.id.personalImage);
        switch(index) {
            case 0: img.setImageResource(R.drawable.chandler);; break;
            case 1: img.setImageResource(R.drawable.joey);; break;
            case 2: img.setImageResource(R.drawable.monica);; break;
            case 3: img.setImageResource(R.drawable.phoebe);; break;
            case 4: img.setImageResource(R.drawable.rachel);; break;
            case 5: img.setImageResource(R.drawable.ross);; break;
            default: break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal, menu);
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
