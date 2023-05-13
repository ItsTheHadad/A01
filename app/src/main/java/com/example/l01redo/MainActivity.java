package com.example.l01redo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.l01redo.Interfaces.SensorCallback;
import com.example.l01redo.Utilities.SensorsDetector;
import com.example.l01redo.Utilities.SignalGenerator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    ShapeableImageView[][] gridMatrix;
    ShapeableImageView[][] prizeMatrix;
    FloatingActionButton[] fabArr;
    ShapeableImageView[] vomitArr;
    ShapeableImageView[] saladArr;

    MaterialTextView inGameScore;

    private GameLogic gameLogic;

    private SensorsDetector sensorsDetector; //dont forget to stop sensor on next activity

    private SignalGenerator signalGenerator;
    public static final String LEVEL= "LEVEL";

    public static final String BUTTON = "BUTTON";


    private final int FAST_MODE = 800 ;
    private final int SLOW_MODE = 1300;
    private int delay;
    boolean isButton;
    boolean isFast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent prevIntent = getIntent();
        setFast(prevIntent.getBooleanExtra(LEVEL,false));
        setButton(prevIntent.getBooleanExtra(BUTTON, false));

        signalGenerator.initSG(this); // have to come first
        signalGenerator = SignalGenerator.getInstance();

        findViews();

        gameLogic = new GameLogic();
        matrixStart();
        initControls();
        initSettings(getIsButton());
        oneStep();


    }

    private void initControls(){
        playerDirections();
        initSensorDetection();
    }

    private void initSettings(boolean isButton){
        if(isButton){
            for(int i = 0; i <fabArr.length; i++){
                fabArr[i].setVisibility(View.VISIBLE);
            }
            if(getIsFast()){
                delay = FAST_MODE;
            }
            else{
                delay = SLOW_MODE;
            }
        }
        else {
            delay = SLOW_MODE;
            sensorsDetector.start();
            for(int i = 0; i <fabArr.length; i++){
                fabArr[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    private void updateScore(){
        inGameScore.setText(""+gameLogic.getScore());
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

    private void changeDelay(int newDelay){
        this.delay = newDelay;
    }

    private void findViews() {

        vomitArr = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_life1), findViewById(R.id.main_IMG_life2),
                findViewById(R.id.main_IMG_life3)
        };

        saladArr = new ShapeableImageView[]{
                findViewById(R.id.salad0), findViewById(R.id.salad1),
                findViewById(R.id.salad2), findViewById(R.id.salad3), findViewById(R.id.salad4)
        };

        gridMatrix = new ShapeableImageView[][]{
                {findViewById(R.id.onion0_0), findViewById(R.id.onion0_1), findViewById(R.id.onion0_2), findViewById(R.id.onion0_3), findViewById(R.id.onion0_4)},
                {findViewById(R.id.onion1_0), findViewById(R.id.onion1_1), findViewById(R.id.onion1_2), findViewById(R.id.onion1_3), findViewById(R.id.onion1_4)},
                {findViewById(R.id.onion2_0), findViewById(R.id.onion2_1), findViewById(R.id.onion2_2), findViewById(R.id.onion2_3), findViewById(R.id.onion2_4)},
                {findViewById(R.id.onion3_0), findViewById(R.id.onion3_1), findViewById(R.id.onion3_2), findViewById(R.id.onion3_3), findViewById(R.id.onion3_4)},
                {findViewById(R.id.onion4_0), findViewById(R.id.onion4_1), findViewById(R.id.onion4_2), findViewById(R.id.onion4_3), findViewById(R.id.onion4_4)},
                {findViewById(R.id.onion5_0), findViewById(R.id.onion5_1), findViewById(R.id.onion5_2), findViewById(R.id.onion5_3), findViewById(R.id.onion5_4)},
                {findViewById(R.id.onion6_0), findViewById(R.id.onion6_1), findViewById(R.id.onion6_2), findViewById(R.id.onion6_3), findViewById(R.id.onion6_4)},
                {findViewById(R.id.salad0), findViewById(R.id.salad1), findViewById(R.id.salad2), findViewById(R.id.salad3), findViewById(R.id.salad4)}};

        fabArr = new FloatingActionButton[]{
                findViewById(R.id.main_FAB_left), findViewById(R.id.main_FAB_right)
        };

        prizeMatrix = new ShapeableImageView[][]{
                {findViewById(R.id.tuna0_0), findViewById(R.id.tuna0_1), findViewById(R.id.tuna0_2), findViewById(R.id.tuna0_3), findViewById(R.id.tuna0_4)},
                {findViewById(R.id.tuna1_0), findViewById(R.id.tuna1_1), findViewById(R.id.tuna1_2), findViewById(R.id.tuna1_3), findViewById(R.id.tuna1_4)},
                {findViewById(R.id.tuna2_0), findViewById(R.id.tuna2_1), findViewById(R.id.tuna2_2), findViewById(R.id.tuna2_3), findViewById(R.id.tuna2_4)},
                {findViewById(R.id.tuna3_0), findViewById(R.id.tuna3_1), findViewById(R.id.tuna3_2), findViewById(R.id.tuna3_3), findViewById(R.id.tuna3_4)},
                {findViewById(R.id.tuna4_0), findViewById(R.id.tuna4_1), findViewById(R.id.tuna4_2), findViewById(R.id.tuna4_3), findViewById(R.id.tuna4_4)},
                {findViewById(R.id.tuna5_0), findViewById(R.id.tuna5_1), findViewById(R.id.tuna5_2), findViewById(R.id.tuna5_3), findViewById(R.id.tuna5_4)},
                {findViewById(R.id.tuna6_0), findViewById(R.id.tuna6_1), findViewById(R.id.tuna6_2), findViewById(R.id.tuna6_3), findViewById(R.id.tuna6_4)},
                {findViewById(R.id.tuna7_0), findViewById(R.id.tuna7_1), findViewById(R.id.tuna7_2), findViewById(R.id.tuna7_3), findViewById(R.id.tuna7_4)}};

        inGameScore = findViewById(R.id.main_TXT_score);

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
                    signalOnHit();
                    gameLogic.onionInSalad = false;
                }
                gameLogic.changeScore(1);
                refreshUi();
                h.postDelayed(this,delay);

                if(gameLogic.isGameOver()){
                    h.removeCallbacks(this); // stop program so it wont be updated
                    doGameOver(); // stop sensor + move activity
                }
            }


        };

        h.post(runnable);
    }

    public void doGameOver(){
        sensorsDetector.stop(); // stop sensor
        moveToGOActivity();
    }


    public void refreshUi(){
        int rows = gameLogic.getROWS();
        int cols = gameLogic.getCOLS();

        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols; j++) {
                if((gameLogic.getMat()[i][j] == GameLogic.state.ONION)||
                        (gameLogic.getMat()[i][j] == GameLogic.state.SALAD)){
                    gridMatrix[i][j].setVisibility(View.VISIBLE);
                    prizeMatrix[i][j].setVisibility(View.INVISIBLE);
                }
                else if(gameLogic.getMat()[i][j] == GameLogic.state.TUNA){
                    prizeMatrix[i][j].setVisibility(View.VISIBLE);
                    gridMatrix[i][j].setVisibility(View.INVISIBLE);

                }

                else if (gameLogic.getMat()[i][j] == GameLogic.state.EMPTY) {
                    gridMatrix[i][j].setVisibility(View.INVISIBLE);
                    prizeMatrix[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }
        updateScore();
        showVomit();

    }

    public void playCrashSound(){
        signalGenerator.getCrashSound().start();
    }


    public void signalOnHit(){
        playCrashSound();
        vibrate();
        toast("Disgusting.");
    }

    public void vibrate(){
        signalGenerator.vibrate();
    }
    public void toast(String str){
        signalGenerator.toast(str);
    }

    private void initSensorDetection(){
        sensorsDetector = new SensorsDetector(this, new SensorCallback() {
            @Override
            public void moveLeft() {
                gameLogic.changeSaladPos(GameLogic.direction.LEFT);
                refreshUi();
            }

            @Override
            public void moveRight() {
                gameLogic.changeSaladPos(GameLogic.direction.RIGHT);
                refreshUi();
            }

            @Override
            public void moveBack() {
                if(delay != FAST_MODE){
                    changeDelay(FAST_MODE);
                    Toast.makeText(getApplicationContext(), "Fast Mode", Toast.LENGTH_SHORT).show();
                    //update the delay?
                }
                else{
                    Toast.makeText(getApplicationContext(), "Max Speed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void moveForw() {
                if(delay != SLOW_MODE){
                    changeDelay(SLOW_MODE);
                    Toast.makeText(getApplicationContext(), "Slow Mode", Toast.LENGTH_SHORT).show();
                    //update the delay?
                }
                else{
                    Toast.makeText(getApplicationContext(), "Min Speed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean getIsButton() {
        return isButton;
    }

    public boolean getIsFast() {
        return isFast;
    }

    public void setButton(boolean button) {
        isButton = button;
    }

    public void setFast(boolean fast) {
        isFast = fast;
    }

    private void moveToGOActivity(){
        Intent GOIntent = new Intent(this, GameOverActivity.class);
        GOIntent.putExtra(GameOverActivity.LEVEL,getIsFast()); // to know what difficulty the user was on
        GOIntent.putExtra(GameOverActivity.BUTTON,getIsButton()); // to know if the user used btns or snsrs
        GOIntent.putExtra(GameOverActivity.SCORE,gameLogic.getScore()); // to know the score of the user
        startActivity(GOIntent);
        finish();
    }

}
