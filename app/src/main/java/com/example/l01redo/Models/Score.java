package com.example.l01redo.Models;

public class Score {

    private String name = "";
    private int score = 0;

    public Score(){
    }


    public String getName() {
        return name;
    }

    public Score setName(String name){
        this.name = name;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Score setScore(int score){
        this.score = score;
        return this;
    }


    @Override
    public String toString() {
        return "Score{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
