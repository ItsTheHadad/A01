package com.example.l01redo.Models;

import java.util.ArrayList;

public class ScoresList {
    private ArrayList<Score> scores;

    public ScoresList() {
        scores = new ArrayList<>();
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

}
