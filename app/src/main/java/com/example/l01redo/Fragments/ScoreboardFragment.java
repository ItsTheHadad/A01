package com.example.l01redo.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.l01redo.Adapters.ScoreAdapter;
import com.example.l01redo.Models.Score;
import com.example.l01redo.R;
import com.example.l01redo.Utilities.SPutil;
import com.example.l01redo.Utilities.ScoreDataManager;

public class ScoreboardFragment extends Fragment {


    private RecyclerView scoreLst;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewSB = inflater.inflate(R.layout.fragment_scoreboard,container,false);
        findView(viewSB);
        initViews();
        return viewSB;
    }

    public void initViews(){
        ScoreAdapter scoreAdapter = new ScoreAdapter(ScoreDataManager.getInstance().getScores());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        scoreLst.setLayoutManager(linearLayoutManager); // wont work without that
        scoreLst.setAdapter(scoreAdapter);

    }

    private void findView(View view){
        scoreLst = view.findViewById(R.id.main_LST_scores);
    }



}