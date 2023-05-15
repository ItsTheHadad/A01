package com.example.l01redo.Utilities;

import android.content.Context;

import com.example.l01redo.Models.Score;
import com.example.l01redo.Models.ScoresList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreDataManager {


    private static ScoreDataManager instance = null;
    private ScoresList scores;

    private ScoreDataManager() {
        scores = new ScoresList();
    }

    public static ScoreDataManager getInstance() {
        if (instance == null) {
            instance = new ScoreDataManager();
            instance.loadSP();
        }
        return instance;
    }

    public ArrayList<Score> getScores() {
        return scores.getScores();
    }

    public void addScore(Score score) {
        this.scores.getScores().add(score);
        if (scores.getScores().size() > 1)
            scores.getScores().sort(new Comparator<Score>() {
                @Override
                public int compare(Score o1, Score o2) {
                    return o1.getScore() - o2.getScore();
                }
            });
        if (scores.getScores().size() > 10) {
            scores.getScores().remove(10);
        }
    }


    public void saveSP() {
        SPutil.getInstance().putObject("scores", scores);
    }

    public void loadSP() {
        scores = (ScoresList) SPutil.getInstance().getObject("scores", new ScoresList() ,ScoresList.class);
    }



}
