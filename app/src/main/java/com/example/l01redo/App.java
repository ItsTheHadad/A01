package com.example.l01redo;

import android.app.Application;

import com.example.l01redo.Utilities.SignalGenerator;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SignalGenerator.initSG(this);
    }
}


