package com.example.feli.ghost;

/**
 * Created by Feli on 30-9-2015.
 */
public class Game {
    private Lexicon lexicon;
    boolean winningPlayer;
    boolean currentPlayer;
    String guesses = "";

    Game(Lexicon lexicon) {
        this.lexicon = lexicon;
    }

    public void guess(String input) {
        guesses = guesses + input;
        lexicon.filter(guesses);
        currentPlayer = !currentPlayer;
    }

    public boolean turn() {
        return currentPlayer;
    }

    public boolean ended() {
        if (lexicon.count() == 0 || (guesses.length() > 3 && lexicon.getHash().contains(guesses))) {
            winningPlayer = !currentPlayer;
            return true;
        } else {
            return false;
        }
    }

    public boolean winner() {
        return winningPlayer;
    }

}
