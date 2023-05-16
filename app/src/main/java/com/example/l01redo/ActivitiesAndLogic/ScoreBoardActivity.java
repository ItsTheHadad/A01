package com.example.l01redo.ActivitiesAndLogic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.l01redo.Fragments.MapFragment;
import com.example.l01redo.Fragments.ScoreboardFragment;
import com.example.l01redo.Interfaces.MapCallback;
import com.example.l01redo.R;
import com.google.android.material.button.MaterialButton;

public class ScoreBoardActivity extends AppCompatActivity {
    private ScoreboardFragment sbFrag;
    private MapFragment mFrag;
    private MaterialButton menuBtn;

    MapCallback mapCallback = new MapCallback() {
        @Override
        public void goTo(double x, double y) {
            mFrag.showLocation(x,y);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        findViews();
        menuBtnClick();
        initFragments();
        beginTransactions();


    }
    private void findViews(){
        menuBtn = findViewById(R.id.sb_BTN_menu);
    }
    private void menuBtnClick(){
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToMenuActivity();
            }
        });
    }
    public void moveToMenuActivity(){
        Intent menuIntent = new Intent(this, MenuActivity.class);
        startActivity(menuIntent);
        finish();
    }

    private void initFragments(){
        sbFrag = new ScoreboardFragment();
        sbFrag.setMapCallback(mapCallback);
        mFrag = new MapFragment();

    }

    private void beginTransactions() {
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_scores, sbFrag).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_map, mFrag).commit();
        //replace maybe instead of add?
    }



}