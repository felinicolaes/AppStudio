package com.example.feli.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class MainGame extends Activity {

    String[] board = new String[10];
    String user = "O";
    int plays = 0;
    Button[] button = new Button[10];

    /* Creates the buttons on the initial board
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        Button backButton = (Button) findViewById(R.id.back_button);
        Button againButton = (Button) findViewById(R.id.again_button);

        button[1] = (Button) findViewById(R.id.button11);
        button[2] = (Button) findViewById(R.id.button12);
        button[3] = (Button) findViewById(R.id.button13);
        button[4] = (Button) findViewById(R.id.button21);
        button[5] = (Button) findViewById(R.id.button22);
        button[6] = (Button) findViewById(R.id.button23);
        button[7] = (Button) findViewById(R.id.button31);
        button[8] = (Button) findViewById(R.id.button32);
        button[9] = (Button) findViewById(R.id.button33);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainGame.this, StartMenu.class);
                MainGame.this.startActivity(myIntent);
            }
        });

        againButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetGame();
            }
        });
    }

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

    /* Handles what happens if one of the 9 play-buttons is pressed
     * @param view  a View which contains the pressed button
     */
    public void buttonHandler(View view) {
        TextView thisButton = (TextView) view;
        int index = Arrays.asList(button).indexOf(thisButton);
        if (index != 0 && board[index] == null) {
            thisButton.setText(user);
            board[index] = user;
            changeUser();
            checkEnd();
            checkWin();
        }
    }

    /* Changes the user from X to O and vice versa
     */
    public void changeUser() {
        plays++;
        if (plays%2 == 0) {
            user = "O";
        } else {
            user = "X";
        }
    }

    /* Checks if the game has ended in a draw and changes the background to red if it did
     * @return boolean  Is true if the game is over and false if the game is not
     */
    public boolean checkEnd() {
        for(int i=1; i<10; i++) {
            if (board[i] == null) {
                return false;
            }
        }
        getWindow().getDecorView().setBackgroundColor(Color.RED);
        return true;
    }

    /* Resets the game so it can be played again
     */
    public void resetGame() {
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        for(int i=1; i<10; i++) {
            button[i].setText(" ");
            board[i] = null;
            plays = 0;
            user = "O";
        }
    }

    /* Checks if someone has won and changes the background to green if someone did.
     * @return boolean  Is true if someone has won and false if not
     */
    public boolean checkWin() {
        if ((board[1] != null && board[2] != null && board[3] != null &&
                (board[1].equals(board[2]) && board[2].equals(board[3]))) ||
            (board[4] != null && board[5] != null && board[6] != null &&
                (board[4].equals(board[5]) && board[5].equals(board[6]))) ||
            (board[7] != null && board[8] != null && board[9] != null &&
                (board[7].equals(board[8]) && board[8].equals(board[9]))) ||
            (board[1] != null && board[4] != null && board[7] != null &&
                    (board[1].equals(board[4]) && board[4].equals(board[7]))) ||
            (board[2] != null && board[5] != null && board[8] != null &&
                    (board[2].equals(board[5]) && board[5].equals(board[8]))) ||
            (board[3] != null && board[6] != null && board[9] != null &&
                    (board[3].equals(board[6]) && board[6].equals(board[9]))) ||
            (board[1] != null && board[5] != null && board[9] != null &&
                    (board[1].equals(board[5]) && board[5].equals(board[9]))) ||
            (board[3] != null && board[5] != null && board[7] != null &&
                    (board[3].equals(board[5]) && board[5].equals(board[7]))) ) {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            return true;
        }
        return false;
    }
}
