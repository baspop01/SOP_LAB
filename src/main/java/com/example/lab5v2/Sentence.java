package com.example.lab5v2;

import java.io.Serializable;
import java.util.ArrayList;

public class Sentence implements Serializable{
    public ArrayList<String> badSentence = new ArrayList<>();
    public ArrayList<String> goodSentence = new ArrayList<>();

    public Sentence(){}

    public Sentence(ArrayList<String> badSentences, ArrayList<String> goodSentence) {
        this.badSentence = badSentences;
        this.goodSentence = goodSentence;
    }

    public ArrayList<String> getBadSentences() {
        return badSentence;
    }

    public void setBadSentences(ArrayList<String> badSentences) {
        this.badSentence = badSentences;
    }

    public ArrayList<String> getGoodSentence() {
        return goodSentence;
    }

    public void setGoodSentence(ArrayList<String> goodSentence) {
        this.goodSentence = goodSentence;
    }
}
