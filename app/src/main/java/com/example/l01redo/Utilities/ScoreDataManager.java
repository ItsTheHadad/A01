package com.example.l01redo.Utilities;

import com.example.l01redo.Models.Score;

import java.util.ArrayList;

public class ScoreDataManager {

    public static ArrayList<Score> getScores(){
        ArrayList<Score> scores = new ArrayList<>();

        scores.add(new Score()
                .setName("Amit")
                .setMode("difficult")
                .setScore(5000)
        );

        return scores;
    }

}
