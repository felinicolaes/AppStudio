package com.example.feli.ghost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Map;

/**
 * GetOldPlayer Activity starts a screen where the player can choose a player from a list with
 * previous players
 */
public class GetOldPlayer extends AppCompatActivity {
    private ArrayList<Player> allPlayers = new ArrayList<>();
    private ArrayList<String> allNames = new ArrayList<>();
    private int player;
    private String otherPlayer;

    /* onCreate: Create all text and buttons and get all information for activity to work
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_old_player);

        Bundle extras = getIntent().getExtras();
        player = extras.getInt("player");
        otherPlayer = extras.getString("otherPlayer");

        getAllPlayers();
        makeNamesList();

        ImageView playerImg = (ImageView) findViewById(R.id.players);
        playerImg.setImageResource(R.drawable.players);
    }

    /* getAllPlayers: Get and save all previous players from the shared preferences
     */
    public void getAllPlayers() {
        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        Map<String, ?> allPrefs = settings.getAll();

        for (Map.Entry<String, ?> entry : allPrefs.entrySet()) {
            if (!entry.getKey().equals("sourcePath") && !entry.getKey().equals("name0") && !entry.getKey().equals("name1") &&
                    !entry.getKey().equals("guesses") && !entry.getKey().equals("turn")) {
                Player p = new Player(entry.getKey(), (Integer) entry.getValue());
                allPlayers.add(p);
                String name = entry.getKey() + ", score: " + entry.getValue();
                allNames.add(name);
            }
        }
    }

    /* makeNamesList: Get all previous players from the allNames list and show them in a listview
     */
    public void makeNamesList() {
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1, allNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choosePlayer(position);
            }
        });
    }

    /* choosePlayer: Set all extras and go to the pickPlayer activity
     */
    public void choosePlayer(int position) {
        Player p = allPlayers.get(position);
        Intent pickPlayerIntent = new Intent(this, PickPlayer.class);
        pickPlayerIntent.putExtra("player", player);
        pickPlayerIntent.putExtra("otherPlayer", otherPlayer);
        pickPlayerIntent.putExtra("score", p.getScore());
        pickPlayerIntent.putExtra("name", p.getName());
        startActivity(pickPlayerIntent);
    }

    /* backButton: Set necessary extras and go to the pickPlayer activity
     */
    public void backButton(View view) {
        Intent pickPlayerIntent = new Intent(this, PickPlayer.class);
        pickPlayerIntent.putExtra("player", player);
        pickPlayerIntent.putExtra("otherPlayer", otherPlayer);
        startActivity(pickPlayerIntent);
    }

    /* * * Create options menu * * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_old_player, menu);
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
