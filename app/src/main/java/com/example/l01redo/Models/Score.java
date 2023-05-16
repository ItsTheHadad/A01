package com.example.l01redo.Models;

public class Score {

    private String name = "";
    private int score = 0;

    private double latitude;
    private double longitude;

    public Score(){
    }


    public double getLatitude() {
        return latitude;
    }

    public Score setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Score setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
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
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
