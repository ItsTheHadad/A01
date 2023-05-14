package com.example.l01redo.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.l01redo.Models.Score;
import com.example.l01redo.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private ArrayList<Score> scores;

    public ScoreAdapter(ArrayList<Score> scores){
        this.scores = scores;
    }


    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_item,parent,false);
        ScoreViewHolder scoreViewHolder = new ScoreViewHolder(view);
        return scoreViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Score score = getItem(position);
        holder.score_LBL_name.setText(score.getName());
        holder.score_LBL_score.setText(score.getScore()+"");
        holder.score_LBL_mode.setText(score.getMode());

    }

    @Override
    public int getItemCount() {
        return this.scores == null ? 0 : this.scores.size();
    }

    private Score getItem(int position){
        return this.scores.get(position);
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView score_LBL_name;
        private MaterialTextView score_LBL_score;
        private MaterialTextView score_LBL_mode;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);

            score_LBL_name = itemView.findViewById(R.id.sb_TXT_name);
            score_LBL_score = itemView.findViewById(R.id.sb_TXT_score);
            score_LBL_mode = itemView.findViewById(R.id.sb_TXT_mode);

            itemView.setOnClickListener(v -> {
                // add the location thingy
            });

        }
    }

}
