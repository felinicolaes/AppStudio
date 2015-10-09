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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class Highscores extends AppCompatActivity {
    String[] players;
    String sourcePath;
    ArrayList<Player> allPlayers = new ArrayList<Player>();
    ArrayList<String> allNames = new ArrayList<String>();
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        final SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        getAllPlayers(settings);
        makeNamesList();
        deleteItem(settings);
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
                String name = entry.getKey() + ", score: " + (Integer) entry.getValue();
                allNames.add(name);
            }
        }
    }

    public void makeNamesList() {
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1, allNames);
        listView.setAdapter(adapter);
        System.out.println("names size in make: " + allNames.size());
        System.out.println("players size in make: " + allPlayers.size());
    }

    public void deleteItem(SharedPreferences settings) {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {
                final SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
                settings.edit().remove(allPlayers.get(position).getName()).commit();
                //             allNames.remove(position);
                //             allPlayers.remove(position);
                System.out.println("names size: " + allNames.size());
                System.out.println("players size: " + allPlayers.size());
                getAllPlayers(settings);
                makeNamesList();
                return false;
            }
        });
    }

    public void againButton(View view) {
        Intent i = new Intent(this, MainGame.class);
        startActivity(i);
    }

    public void settingsButton(View view) {
        Intent i = new Intent(this, PickPlayer.class);
        startActivity(i);
    }

    public void menuButton(View view) {
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
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
