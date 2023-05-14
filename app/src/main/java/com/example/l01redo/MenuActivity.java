package com.example.l01redo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.nio.file.Files;

public class MenuActivity extends AppCompatActivity {

    MaterialButton[] buttonModeArrBtn;
    MaterialButton sensorModeBtn;
    MaterialButton startBtn;
    MaterialButton scoreBtn;

    boolean isButton;
    boolean isFast;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViews();
        setButton(true);//default
        setFast(false);//default

        startAllButtons();

    }


    public void startAllButtons(){
        buttonModeClick();
        sensorModeClick();
        startBtnClick();
        scoreBtnClick();

    }

    public boolean getIsFast() {
        return isFast;
    }

    public void setFast(boolean fast) {
        isFast = fast;
    }

    public boolean getIsButton() {
        return isButton;
    }

    public void setButton(boolean button) {
        isButton = button;
    }

    private void findViews(){
        buttonModeArrBtn = new MaterialButton[]{
                findViewById(R.id.menu_BTN_slow), findViewById(R.id.menu_BTN_fast)
        };

        sensorModeBtn = findViewById(R.id.menu_BTN_sensors);

        startBtn = findViewById(R.id.menu_BTN_start);

        scoreBtn = findViewById(R.id.menu_BTN_score);
    }

    private void buttonModeClick(){

        for (MaterialButton btn: buttonModeArrBtn) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btn == findViewById(R.id.menu_BTN_slow)){
                        setFast(false);
                        setButton(true);

                    }
                    else if (btn == findViewById(R.id.menu_BTN_fast)){
                        setFast(true);
                        setButton(true);
                    }
                }
            });

        }


    }

    private void sensorModeClick(){
        sensorModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFast(false);
                setButton(false);
            }
        });
    }

    private void startBtnClick(){
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToMainActivity();
            }
        });
    }

    private void scoreBtnClick(){
        scoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToScoreBoardActivity();
            }
        });
    }

    private void moveToMainActivity(){
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra(MainActivity.LEVEL,getIsFast());
        mainIntent.putExtra(MainActivity.BUTTON,getIsButton());
        startActivity(mainIntent);
        finish();
    }

    private void moveToScoreBoardActivity(){
        Intent sbIntent = new Intent(this, ScoreBoardActivity.class);

        startActivity(sbIntent);
        finish();
    }



}
