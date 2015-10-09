package com.example.feli.ghost;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Feli on 28-9-2015.
 */
public class Lexicon {
    private Set<String> lexiconHash;
    private Set<String> lexiconCopy;
    Iterator<String> it;

    Lexicon(String sourcePath, Context myContext) {
        try {
            InputStream IStream = myContext.getAssets().open(sourcePath);
            InputStreamReader IStreamReader = new InputStreamReader(IStream);
            BufferedReader bReader = new BufferedReader(IStreamReader);
            lexiconHash = new HashSet<>();
            String word = bReader.readLine();
            while (!(word==null)) {
                lexiconHash.add(word);
                word = bReader.readLine();
            }
            IStream.close();
            IStreamReader.close();
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        copyHash(lexiconHash);
    }


    public void copyHash(Set<String> lexicon) {
        lexiconCopy = new HashSet<String>();
        for (it = lexiconHash.iterator(); it.hasNext();) {
            String word = it.next();
            lexiconCopy.add(word);
        }
    }

    public void filter(String input) {
        System.out.println("begin filter");
            lexiconHash = new HashSet<String>();
            for (it = lexiconCopy.iterator(); it.hasNext();) {
                String word = it.next();
                if (word.startsWith(input)) {
                    lexiconHash.add(word);
                }
            }
        System.out.println("lexiconHash size is " + lexiconHash.size());
    }

    public int count() {
        return lexiconHash.size();
    }

    public String result() {
        if (lexiconHash.size() == 1) {
            String value = lexiconHash.toArray(new String[1])[0];
            return value;
        }
        return null;
    }

    public void reset() {
        lexiconHash = lexiconCopy;
        System.out.println("copy size = " + lexiconCopy.size());
        System.out.println("hash size = " + lexiconHash.size());
    }

    public Set<String> getHash() {
        return lexiconHash;
    }
}
