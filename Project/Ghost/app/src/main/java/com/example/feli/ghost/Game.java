package com.example.feli.ghost;

/**
 * Created by Feli on 30-9-2015.
 */
public class Game {
    private Lexicon lexicon;
    boolean winningPlayer;
    boolean currentPlayer;
    String guesses;

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
            winningPlayer = currentPlayer;
            return true;
        } else {
            return false;
        }
    }

    public void setTurn(Boolean newTurn) {
        currentPlayer = newTurn;
    }

    public void setGuesses(String newGuesses) {
        guesses = newGuesses;
    }

    public boolean winner() {
        return winningPlayer;
    }

    public String endReason() {
        String reason = "";
        if (lexicon.count() == 0) {
            reason = "no words found beginning with " + guesses;
        } else if (lexicon.getHash().contains(guesses)) {
            reason = guesses + " is a word";
        }
        return reason;
    }

}
