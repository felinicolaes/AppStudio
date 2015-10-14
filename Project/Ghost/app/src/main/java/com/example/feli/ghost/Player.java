package com.example.feli.ghost;

/**
 * Player Class makes new players and edit/get their information
 */
public class Player {
    private String name;
    private int highscore;

    /* Player: creates a new player object with a specified name and highscore
     */
    Player(String name, int highscore) {
        this.name = name;
        this.highscore = highscore;
    }

    /* addScore: add a certain amount to the previous highscore of a certain player
     */
    public void addScore(int newHighscore) {
        this.highscore = this.highscore + newHighscore;
    }

    /* getScore: return the highscore of a certain player
     */
    public int getScore() {
        return this.highscore;
    }

    /* setName: change the name of a certain player
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /* getName: return the name of a certain player
     */
    public String getName() {
        return this.name;
    }
}
