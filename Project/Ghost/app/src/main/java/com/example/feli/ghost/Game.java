package com.example.feli.ghost;

/**
 * Game Class creates a new game and can change the turns etc.
 */
public class Game {
    private Lexicon lexicon;
    private boolean currentPlayer;
    private String alreadyGuessed;

    /* Game: creates a new game with a lexicon
     */
    Game(Lexicon lexicon) {
        this.lexicon = lexicon;
    }

    /* guess: handles a turn in the game
     */
    public void guess(String input) {
        alreadyGuessed = alreadyGuessed + input;
        lexicon.filter(alreadyGuessed);
        currentPlayer = !currentPlayer;
    }

    /* turn: returns current player
     */
    public boolean turn() {
        return currentPlayer;
    }

    /* ended: returns 'true' if the game has ended
     */
    public boolean ended() {
        if (lexicon.count() == 0 || (alreadyGuessed.length() > 3 && lexicon.getHash().contains(alreadyGuessed))) {
            return true;
        } else {
            return false;
        }
    }

    /* setTurn: sets the turn to the specified person
     */
    public void setTurn(Boolean newTurn) {
        currentPlayer = newTurn;
    }

    /* setAlreadyGuessed: set the guessed word to the specified string
     */
    public void setAlreadyGuessed(String newAlreadyGuessed) {
        alreadyGuessed = newAlreadyGuessed;
    }

    /* winner: returns the winner of the game
     */
    public boolean winner() {
        return currentPlayer;
    }

    /* endReason: returns the reason why the game has ended
     */
    public String endReason() {
        String reason = "";
        if (lexicon.count() == 0) {
            reason = "no words found beginning with " + alreadyGuessed;
        } else if (lexicon.getHash().contains(alreadyGuessed)) {
            reason = alreadyGuessed + " is a word";
        }
        return reason;
    }

}
