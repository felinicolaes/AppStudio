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

import java.util.ArrayList;
import java.util.Map;

public class GetOldPlayer extends AppCompatActivity {
    ArrayList<Player> allPlayers = new ArrayList<Player>();
    ArrayList<String> allNames = new ArrayList<String>();
    int player;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_old_player);

        Bundle extras = getIntent().getExtras();
        player = extras.getInt("player");

        getAllPlayers();
        makeNamesList();

        name = "Klaas";
    }

    public void makeNamesList() {
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1, allNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pickPlayer(position);
            }
        });
    }

    public void getAllPlayers() {
        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        Map<String, ?> allPrefs = settings.getAll();

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

    public void pickPlayer(int position) {
        Player p = allPlayers.get(position);
        Intent i = new Intent(this, PickPlayer.class);
        i.putExtra("player", player);
        i.putExtra("name", p.getName());
        startActivity(i);
    }

  /*  public void chooseButton(View view) {
        Intent i = new Intent(this, PickPlayer.class);
        i.putExtra("player", player);
        i.putExtra("name", name);
        startActivity(i);
    } */

    public void backButton(View view) {
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
    }

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
