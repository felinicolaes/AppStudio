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
 * Highscores Activity starts a screen where the player see the highscores in a list with previous
 * players
 */
public class Highscores extends AppCompatActivity {
    private ArrayList<Player> allPlayers = new ArrayList<>();
    private ArrayList<String> allNames = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        ImageView img= (ImageView) findViewById(R.id.highscores);
        img.setImageResource(R.drawable.highscores);

        final SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        getAllPlayers(settings);
        makeNamesList();
        deleteItem();
    }

    public void getAllPlayers(SharedPreferences settings) {
        Map<String, ?> allPrefs = settings.getAll();
        allPlayers.clear();
        allNames.clear();

        for (Map.Entry<String, ?> entry : allPrefs.entrySet()) {
            if (!entry.getKey().equals("sourcePath") && !entry.getKey().equals("name0") && !entry.getKey().equals("name1") &&
                    !entry.getKey().equals("guesses") && !entry.getKey().equals("turn")) {
                Player p = new Player(entry.getKey(), (Integer) entry.getValue());
                allPlayers.add(p);
                String name = entry.getKey() + ", score: " + entry.getValue();
                allNames.add(name);
            }
        }
        sortPlayers();
    }

    public void sortPlayers() {
        boolean sorted = true;
        Player tempPlayer;
        String tempName;
        int j = 0;
        while (sorted) {
            sorted = false;
            j++;
            for (int i = 0; i < allPlayers.size() - j; i++) {
                if (allPlayers.get(i).getScore() < allPlayers.get(i+1).getScore()) {
                    tempPlayer = allPlayers.get(i);
                    allPlayers.set(i, allPlayers.get(i+1));
                    allPlayers.set(i+1, tempPlayer);
                    tempName = allNames.get(i);
                    allNames.set(i, allNames.get(i+1));
                    allNames.set(i+1, tempName);
                    sorted = true;
                }
            }
        }
    }

    public void makeNamesList() {
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1, allNames);
        listView.setAdapter(adapter);
    }

    public void deleteItem() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {
                final SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
                settings.edit().remove(allPlayers.get(position).getName()).commit();
                getAllPlayers(settings);
                makeNamesList();
                return false;
            }
        });
    }

    /*
     * Create buttons
     */
    public void againButton(View view) {
        Intent mainGameIntent = new Intent(this, MainGame.class);
        startActivity(mainGameIntent);
    }

    public void settingsButton(View view) {
        Intent pickPlayerIntent = new Intent(this, PickPlayer.class);
        startActivity(pickPlayerIntent);
    }

    public void menuButton(View view) {
        Intent mainMenuIntent = new Intent(this, MainMenu.class);
        startActivity(mainMenuIntent);
    }

    /*
     * Create options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_highscores, menu);
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
