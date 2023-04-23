package com.example.l01redo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    ShapeableImageView[][] gridMatrix;
    FloatingActionButton[] fabArr;
    ShapeableImageView[] vomitArr;
    ShapeableImageView[] saladArr;

    private GameLogic gameLogic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        gameLogic = new GameLogic();
        matrixStart();
        playerDirections();
        oneStep();

    }

    private void showVomit(){
        if(gameLogic.getPukeLeft() >= gameLogic.getTOTAL_PUKE()){
            vomitArr[0].setVisibility(View.VISIBLE);
            vomitArr[1].setVisibility(View.VISIBLE);
            vomitArr[2].setVisibility(View.VISIBLE);
        }
        else if(gameLogic.getPukeLeft() == 2){
            vomitArr[0].setVisibility(View.VISIBLE);
            vomitArr[1].setVisibility(View.VISIBLE);
            vomitArr[2].setVisibility(View.INVISIBLE);
        }
        else if(gameLogic.getPukeLeft() == 1){
            vomitArr[0].setVisibility(View.VISIBLE);
            vomitArr[1].setVisibility(View.INVISIBLE);
            vomitArr[2].setVisibility(View.INVISIBLE);
        }
        else{
            vomitArr[0].setVisibility(View.INVISIBLE);
            vomitArr[1].setVisibility(View.INVISIBLE);
            vomitArr[2].setVisibility(View.INVISIBLE);
        }

    }

    private void findViews() {

        vomitArr = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_life1), findViewById(R.id.main_IMG_life2),
                findViewById(R.id.main_IMG_life3)
        };

        saladArr = new ShapeableImageView[]{
                findViewById(R.id.salad0), findViewById(R.id.salad1),
                findViewById(R.id.salad2), findViewById(R.id.salad3),  findViewById(R.id.salad4)
        };

        gridMatrix = new ShapeableImageView[][]{
                {findViewById(R.id.onion0_0), findViewById(R.id.onion0_1), findViewById(R.id.onion0_2), findViewById(R.id.onion0_3), findViewById(R.id.onion0_4)},
                {findViewById(R.id.onion1_0), findViewById(R.id.onion1_1), findViewById(R.id.onion1_2), findViewById(R.id.onion1_3), findViewById(R.id.onion1_4)},
                {findViewById(R.id.onion2_0), findViewById(R.id.onion2_1), findViewById(R.id.onion2_2), findViewById(R.id.onion2_3), findViewById(R.id.onion2_4)},
                {findViewById(R.id.onion3_0), findViewById(R.id.onion3_1), findViewById(R.id.onion3_2), findViewById(R.id.onion3_3), findViewById(R.id.onion3_4)},
                {findViewById(R.id.onion4_0), findViewById(R.id.onion4_1), findViewById(R.id.onion4_2), findViewById(R.id.onion4_3), findViewById(R.id.onion4_4)},
                {findViewById(R.id.onion5_0), findViewById(R.id.onion5_1), findViewById(R.id.onion5_2), findViewById(R.id.onion5_3), findViewById(R.id.onion5_4)},
                {findViewById(R.id.onion6_0), findViewById(R.id.onion6_1), findViewById(R.id.onion6_2), findViewById(R.id.onion6_3), findViewById(R.id.onion6_4)},
                {findViewById(R.id.salad0), findViewById(R.id.salad1), findViewById(R.id.salad2)}};

        fabArr = new FloatingActionButton[]{
               findViewById(R.id.main_FAB_left),findViewById(R.id.main_FAB_right)
       };



    }


    public void playerDirections(){

        for (FloatingActionButton btn: fabArr) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btn == findViewById(R.id.main_FAB_right)){
                        gameLogic.changeSaladPos(GameLogic.direction.RIGHT);
                    }
                    else if (btn == findViewById(R.id.main_FAB_left)){
                        gameLogic.changeSaladPos(GameLogic.direction.LEFT);
                    }
                    refreshUi();
                }
            });
        }
    }

    public void matrixStart(){
        gameLogic.setMat();
        refreshUi();
    }


    public void oneStep(){
        Handler h = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                gameLogic.oneStepOnion();
                if(gameLogic.onionInSalad){
                    vibrateAndToast();
                    gameLogic.onionInSalad = false;
                }
                refreshUi();
                h.postDelayed(this,1000);
            }
        };
        h.post(runnable);
    }


    public void refreshUi(){
        int rows = gameLogic.getROWS();
        int cols = gameLogic.getCOLS();

        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols; j++) {
                if((gameLogic.getMat()[i][j] == GameLogic.state.ONION)||
                        (gameLogic.getMat()[i][j] == GameLogic.state.SALAD)){
                    gridMatrix[i][j].setVisibility(View.VISIBLE);
                } else if (gameLogic.getMat()[i][j] == GameLogic.state.EMPTY) {
                    gridMatrix[i][j].setVisibility(View.INVISIBLE);
                }
                else{

                }
            }
        }

        showVomit();

    }

    public void vibrate(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        }
        else {
            v.vibrate(500);

        }
    }

    private void vibrateAndToast(){
        vibrate();
        Toast.makeText(getApplicationContext(), "Disgusting.", Toast.LENGTH_SHORT).show();
    }

}
