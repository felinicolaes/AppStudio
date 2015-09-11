/* First app, Feli Nicolaes <feli.nicolaes@student.uva.nl>
 * You can type in your name and it will remember it. You can also change the 
 * background color of the app.
 */


package com.example.feli.firstapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView mainTextView;
    Button mainButton;
    Button secondButton;
    Button thirdButton;
    EditText mainEditText;

	/* 
	 * Creates the main frame with five widgets
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTextView = (TextView) findViewById(R.id.main_textview);

        mainButton = (Button) findViewById(R.id.main_button);
        mainButton.setOnClickListener(this);

        secondButton = (Button) findViewById(R.id.second_button);
        secondButton.setOnClickListener(this);

        thirdButton = (Button) findViewById(R.id.third_button);
        thirdButton.setOnClickListener(this);

        mainEditText = (EditText) findViewById(R.id.main_edittext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

	/* 
	 * Contains the actions for the different buttons
	 */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
			// Your name will be remembered if you type it in the EditText widget
            case R.id.main_button:
                mainTextView.setText("Welkom, " + mainEditText.getText().toString());
                break;

			// The backgroundcolor will change to red
            case R.id.second_button:
                getWindow().getDecorView().setBackgroundColor(Color.RED);
                break;

			// The backgroundcolor will change to blue
            case R.id.third_button:
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                break;
        }
    }
}
