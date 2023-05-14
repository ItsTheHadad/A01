package com.example.l01redo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.l01redo.Fragments.MapFragment;
import com.example.l01redo.Fragments.ScoreboardFragment;

public class ScoreBoardActivity extends AppCompatActivity {

    private ScoreboardFragment sbFrag;
    private MapFragment mFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        initFragments();
        beginTransactions();

    }

    private void initFragments(){
        sbFrag = new ScoreboardFragment();
        mFrag = new MapFragment();
    }

    private void beginTransactions() {
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_scores, sbFrag).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_map, mFrag).commit();
    }

}