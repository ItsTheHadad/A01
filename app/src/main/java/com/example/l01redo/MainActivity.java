package com.example.l01redo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    ShapeableImageView[][] gridMatrix;
    FloatingActionButton[] fabArr;
    ShapeableImageView[] vomitArr;

    ShapeableImageView[] saladArr;

    private final int ROWS = 6;
    private final int COLS = 3;

    int currSalad = 1; //default in the middle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

    }
    

    private void findViews() {

        vomitArr = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_life1), findViewById(R.id.main_IMG_life2),
                findViewById(R.id.main_IMG_life3)
        };

        saladArr = new ShapeableImageView[]{
                findViewById(R.id.salad0), findViewById(R.id.salad1),
                findViewById(R.id.salad2)
        };

        gridMatrix = new ShapeableImageView[][]{
                {findViewById(R.id.onion0_0), findViewById(R.id.onion0_1), findViewById(R.id.onion0_2)},
                {findViewById(R.id.onion1_0), findViewById(R.id.onion1_1), findViewById(R.id.onion1_2)},
                {findViewById(R.id.onion2_0), findViewById(R.id.onion2_1), findViewById(R.id.onion2_2)},
                {findViewById(R.id.onion3_0), findViewById(R.id.onion3_1), findViewById(R.id.onion3_2)},
                {findViewById(R.id.onion4_0), findViewById(R.id.onion4_1), findViewById(R.id.onion4_2)},
                {findViewById(R.id.salad0), findViewById(R.id.salad1), findViewById(R.id.salad2)}};

        fabArr = new FloatingActionButton[]{
               findViewById(R.id.main_FAB_left),findViewById(R.id.main_FAB_right)
       };


    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLS() {
        return COLS;
    }

    public void playerDirections(){
        for (int i = 0; i < getCOLS(); i++) {
            if(gridMatrix[getROWS()-1][i].getVisibility() == View.VISIBLE){
                currSalad = i;
            }
            
        }

        for (FloatingActionButton btn: fabArr) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currSalad == 0){
                        if (btn == findViewById(R.id.main_FAB_right)){
                            gridMatrix[getROWS()-1][0].setVisibility(View.INVISIBLE);
                            gridMatrix[getROWS()-1][1].setVisibility(View.VISIBLE);
                            return;
                        }
                        return;
                    }
                    if (currSalad == 2){
                        if (btn == findViewById(R.id.main_FAB_left)){
                            gridMatrix[getROWS()-1][2].setVisibility(View.INVISIBLE);
                            gridMatrix[getROWS()-1][1].setVisibility(View.VISIBLE);
                            return;
                        }
                        return;
                    }
                    if (currSalad == 1){
                        if (btn == findViewById(R.id.main_FAB_left)){
                            gridMatrix[getROWS()-1][1].setVisibility(View.INVISIBLE);
                            gridMatrix[getROWS()-1][0].setVisibility(View.VISIBLE);
                            return;
                        }
                        else if (btn == findViewById(R.id.main_FAB_right)) {
                            gridMatrix[getROWS() - 1][1].setVisibility(View.INVISIBLE);
                            gridMatrix[getROWS() - 1][2].setVisibility(View.VISIBLE);
                            return;
                        }
                        return;
                    }

                }
            });
        }


        
        
    }

    public void matrixStart(){
        for (int rows = 0; rows < getROWS()-1 ; rows++) {
            for (int cols = 0; cols < getCOLS(); cols++) {
                gridMatrix[rows][cols].setVisibility(View.INVISIBLE);
            }
        }
        gridMatrix[getROWS()][0].setVisibility(View.INVISIBLE);
        gridMatrix[getROWS()][1].setVisibility(View.VISIBLE);
        gridMatrix[getROWS()][2].setVisibility(View.INVISIBLE);
    }

    public void randomOnionAppear(){
        Handler h = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int col = (int)(Math.random() * ((getCOLS()-1) + 1));
                gridMatrix[0][col].setVisibility(View.VISIBLE);
                h.postDelayed(this,5000);

            }
        };
        h.post(runnable);
    }






}
