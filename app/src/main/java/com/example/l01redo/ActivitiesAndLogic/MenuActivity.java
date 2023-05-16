package com.example.l01redo.ActivitiesAndLogic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.example.l01redo.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class MenuActivity extends AppCompatActivity {

    private AppCompatImageView menu_IMG_background;

    MaterialTextView appName;
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
        glideImpl();
        setButton(true);//default
        setFast(false);//default

        startAllButtons();

    }


    public void startAllButtons(){
        startBtn.setVisibility(View.INVISIBLE);
        buttonModeClick();
        sensorModeClick();
        startBtnClick();
        scoreBtnClick();

    }

    public void glideImpl(){
        Glide
                .with(this)
                .load(R.drawable.ic_background_tomato)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(menu_IMG_background);
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
        appName = findViewById(R.id.menu_LBL_gameName);

        menu_IMG_background = findViewById(R.id.menu_IMG_background);

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
                    startBtn.setVisibility(View.VISIBLE);

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

                startBtn.setVisibility(View.VISIBLE);
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
