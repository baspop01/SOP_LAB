package com.example.lab5v2;

import java.io.Serializable;
import java.util.ArrayList;

public class Word implements Serializable {
    public ArrayList<String> badWords = new ArrayList<>();;
    public ArrayList<String> goodWords = new ArrayList<>();;


    public Word(){
        this.goodWords.add("happy");
        this.goodWords.add("enjoy");
        this.goodWords.add("life");
        this.badWords.add("fuck");
        this.badWords.add("olo");
    }
    public Word(ArrayList badWords, ArrayList goodWords){
        this.badWords = badWords;
        this.goodWords = goodWords;
    }

    public ArrayList getBadwords() {
        return badWords;
    }

    public void setBadwords(ArrayList badWords) {
        this.badWords = badWords;
    }

    public ArrayList getGoodwords() {
        return goodWords;
    }

    public void setGoodwords(ArrayList goodWords) {
        this.goodWords = goodWords;
    }
}
