package com.example.feli.ghost;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Ghost App - Feli Nicolaes, feli.nicolaes@uva.student.nl
 *
 * Lexicon Class creates a lexicon using the words from the txt files and the functions to use
 * and filter it
 */
public class Lexicon {
    private Set<String> remainingWords;
    private Set<String> allWords;
    private Iterator<String> it;

    /* Lexicon: creates a hash set with all the necessary words from the specified text file
     */
    Lexicon(String sourcePath, Context myContext) {
        try {
            InputStream IStream = myContext.getAssets().open(sourcePath);
            InputStreamReader IStreamReader = new InputStreamReader(IStream);
            BufferedReader bReader = new BufferedReader(IStreamReader);
            allWords = new HashSet<>();
            String word = bReader.readLine();

            //words with 3 or less letters are not important, so not added. The same goes for
            //strings that begin with an already parsed word
            String previousString = "x";
            while (!(word==null)) {
                if (word.length() > 3 && !word.startsWith(previousString)) {
                    allWords.add(word);
                    previousString = word;
                }
                word = bReader.readLine();
            }
            IStream.close();
            IStreamReader.close();
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        copyHash();
    }

    /* copyHash: copy all words from the original hash set into a new hash set
     */
    public void copyHash() {
        remainingWords = new HashSet<>();
        for (it = allWords.iterator(); it.hasNext();) {
            String word = it.next();
            remainingWords.add(word);
        }
    }

    /* filter: filter the hash set so a hash set only containing words starting with a certain
     * beginning is made
     */
    public void filter(String startsWith) {
        remainingWords = new HashSet<>();
        for (it = allWords.iterator(); it.hasNext();) {
            String word = it.next();
            if (word.startsWith(startsWith)) {
                remainingWords.add(word);
            }
        }
    }

    /* count: return the amount of words left in the filtered hash set
     */
    public int count() {
        return remainingWords.size();
    }

    /* getHash: return hash set of the filtered list
     */
    public Set<String> getHash() {
        return remainingWords;
    }
}
