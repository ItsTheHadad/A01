package com.example.l01redo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class GameOverActivity extends AppCompatActivity {

    MaterialTextView scoreChangingTxt;
    MaterialTextView nameTxt;
    TextInputEditText enterNameInput;
    MaterialButton saveBtn;
    MaterialButton[] screenBtns;

    public static final String LEVEL= "LEVEL";

    public static final String BUTTON = "BUTTON";

    public static final String SCORE = "SCORE";


    private boolean isFast;
    private boolean isButton;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        findViews();

        Intent prevIntent = getIntent();
        setFast(prevIntent.getBooleanExtra(LEVEL,false));
        setButton(prevIntent.getBooleanExtra(BUTTON, false));
        setScore(prevIntent.getIntExtra(SCORE,0));

        showScore();


    }

    public boolean isFast() {
        return isFast;
    }

    public void setFast(boolean fast) {
        isFast = fast;
    }

    public boolean isButton() {
        return isButton;
    }

    public void setButton(boolean button) {
        isButton = button;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void findViews(){
        scoreChangingTxt = findViewById(R.id.go_TXT_score);

        nameTxt = findViewById(R.id.go_LBL_enter);

        enterNameInput = findViewById(R.id.go_ET_enter);

        saveBtn = findViewById(R.id.go_BTN_save);

        screenBtns = new MaterialButton[]{
                findViewById(R.id.go_BTN_scoreboard),  findViewById(R.id.go_BTN_menu)
        };

    }

    public void showScore(){
        scoreChangingTxt.setText(""+getScore());
    }

}