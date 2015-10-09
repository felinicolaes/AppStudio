package com.example.feli.ghost;

import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Feli on 8-10-2015.
 */
public class Player {
    String name;
    int highscore;

    Player(String name, int highscore) {
        this.name = name;
        this.highscore = highscore;
    }

    public void addScore(int newHighscore) {
        this.highscore = this.highscore + newHighscore;
    }

    public int getScore() {
        return this.highscore;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }
}
