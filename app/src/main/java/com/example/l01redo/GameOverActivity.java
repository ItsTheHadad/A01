package com.example.l01redo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.l01redo.Models.Score;
import com.example.l01redo.Utilities.SPutil;
import com.example.l01redo.Utilities.ScoreDataManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class GameOverActivity extends AppCompatActivity {

    MaterialTextView scoreChangingTxt;
    MaterialTextView nameTxt;
    TextInputEditText enterNameInput;
    MaterialButton saveBtn;
    MaterialButton scrBtn;
    MaterialButton menuBtn;

    public static final String SCORE = "SCORE";
    private int score;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        initView();

        Intent prevIntent = getIntent();
        setScore(prevIntent.getIntExtra(SCORE,0));

        showScore();


    }

    public void saveBtnClick(){
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempName = enterNameInput.getText().toString();
                if(!tempName.isEmpty()){

                    setName(tempName);
                    setScore(Integer.parseInt(scoreChangingTxt.getText().toString()));

                    addScore();

                    menuBtn.setVisibility(View.VISIBLE);
                    scrBtn.setVisibility(View.VISIBLE);
                    nameTxt.setText("Score Saved "+getName());
                    enterNameInput.setVisibility(View.INVISIBLE);
                    saveBtn.setVisibility(View.INVISIBLE);

                }
                else{
                    //red notification;
                }
            }
        });
    }
    public void menuBtnClick(){
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToMenuActivity();
            }
        });
    }
    public void scoreBtnClick(){
        scrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void initView(){
        findViews();
        menuBtn.setVisibility(View.INVISIBLE);
        scrBtn.setVisibility(View.INVISIBLE);
        saveBtnClick();
        menuBtnClick();
        scoreBtnClick();
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

        scrBtn = findViewById(R.id.go_BTN_scoreboard);

        menuBtn = findViewById(R.id.go_BTN_menu);

    }

    public void showScore(){
        scoreChangingTxt.setText(""+getScore());
    }

    public void moveToMenuActivity(){
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
        finish();
    }

    public void addScore(){
        Score newScore = new Score().setScore(score).setName(name);

        ScoreDataManager.getInstance().addScore(newScore);
        ScoreDataManager.getInstance().saveSP();
    }

}