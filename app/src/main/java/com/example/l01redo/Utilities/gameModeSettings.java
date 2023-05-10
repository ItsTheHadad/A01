package com.example.l01redo.Utilities;

public class gameModeSettings {

    private final int FAST_MODE = 800 ;
    private final int SLOW_MODE = 1300;
    private int delay = SLOW_MODE; //deafult is slow on sensor
    boolean isButton = false; // false = sensor








    public int getFAST_MODE() {
        return FAST_MODE;
    }

    public int getSLOW_MODE() {
        return SLOW_MODE;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int newDelay){
        this.delay = newDelay;
    }

    public boolean isButton() {
        return isButton;
    }

    public void setIsButton(boolean newIsButton) {
        isButton = newIsButton;
    }
}
