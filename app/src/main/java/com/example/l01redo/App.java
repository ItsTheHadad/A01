package com.example.l01redo;

import android.app.Application;

import com.example.l01redo.Utilities.SPutil;
import com.example.l01redo.Utilities.ScoreDataManager;
import com.example.l01redo.Utilities.SignalGenerator;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SPutil.initSP(this);
        SignalGenerator.initSG(this);
        ScoreDataManager.getInstance();
    }
}


