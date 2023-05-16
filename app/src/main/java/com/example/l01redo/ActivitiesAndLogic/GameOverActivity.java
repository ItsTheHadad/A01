package com.example.l01redo.ActivitiesAndLogic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.l01redo.Models.Score;
import com.example.l01redo.R;
import com.example.l01redo.Utilities.Mutil;
import com.example.l01redo.Utilities.ScoreDataManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class GameOverActivity extends AppCompatActivity {

    private AppCompatImageView go_IMG_background;
    private Mutil mutil;
    MaterialTextView scoreChangingTxt;
    MaterialTextView nameTxt;
    TextInputEditText enterNameInput;
    MaterialTextView errorTxt;
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
        findViews();
        glideImpl();
        initView();
        mutil = new Mutil(this);
        Intent prevIntent = getIntent();
        setScore(prevIntent.getIntExtra(SCORE,0));

        showScore();


    }

    public void glideImpl(){
        Glide
                .with(this)
                .load(R.drawable.ic_background_tomato)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(go_IMG_background);
    }

    public void saveBtnClick(){
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempName = enterNameInput.getText().toString();
                if(!tempName.isEmpty()){
                    mutil.getUserLocation(GameOverActivity.this);

                    setName(tempName);


                    setScore(Integer.parseInt(scoreChangingTxt.getText().toString()));

                    addScore();

                    menuBtn.setVisibility(View.VISIBLE);
                    scrBtn.setVisibility(View.VISIBLE);
                    nameTxt.setText("Score Saved As "+getName());
                    enterNameInput.setVisibility(View.INVISIBLE);
                    saveBtn.setVisibility(View.INVISIBLE);
                    errorTxt.setVisibility(View.INVISIBLE);



                }
                else{
                    errorTxt.setVisibility(View.VISIBLE);
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
                moveToSBActivity();
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
        menuBtn.setVisibility(View.INVISIBLE);
        scrBtn.setVisibility(View.INVISIBLE);
        errorTxt.setVisibility(View.INVISIBLE);
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
        go_IMG_background = findViewById(R.id.go_IMG_background);

        scoreChangingTxt = findViewById(R.id.go_TXT_score);

        nameTxt = findViewById(R.id.go_LBL_enter);

        enterNameInput = findViewById(R.id.go_ET_enter);

        saveBtn = findViewById(R.id.go_BTN_save);

        scrBtn = findViewById(R.id.go_BTN_scoreboard);

        menuBtn = findViewById(R.id.go_BTN_menu);

        errorTxt = findViewById(R.id.go_LBL_error);

    }

    public void showScore(){
        scoreChangingTxt.setText(""+getScore());
    }

    public void moveToMenuActivity(){
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
        finish();
    }
    public void moveToSBActivity(){
        Intent sbIntent = new Intent(this, ScoreBoardActivity.class);
        startActivity(sbIntent);
        finish();
    }

    public void addScore(){
        Score newScore = new Score().setScore(score).setName(name)
                .setLatitude(mutil.getLatitude()).setLongitude(mutil.getLongitude());

        ScoreDataManager.getInstance().addScore(newScore);
        ScoreDataManager.getInstance().saveSP();
    }

}