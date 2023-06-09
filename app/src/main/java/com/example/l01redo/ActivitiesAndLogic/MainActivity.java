package com.example.l01redo.ActivitiesAndLogic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.l01redo.Interfaces.SensorCallback;
import com.example.l01redo.R;
import com.example.l01redo.Utilities.SensorsDetector;
import com.example.l01redo.Utilities.SignalGenerator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    private AppCompatImageView main_IMG_background;
    ShapeableImageView[][] gridMatrix;
    ShapeableImageView[][] prizeMatrix;
    FloatingActionButton[] fabArr;
    ShapeableImageView[] vomitArr;
    ShapeableImageView[] saladArr;

    MaterialTextView inGameScore;

    private GameLogic gameLogic;

    private SensorsDetector sensorsDetector;

    public static final String LEVEL= "LEVEL";

    public static final String BUTTON = "BUTTON";


    private final int FAST_MODE = 500 ;
    private final int SLOW_MODE = 800;
    private int delay;
    boolean isButton;
    boolean isFast;

    boolean isPaused;

    private  final Handler h = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            h.postDelayed(this,delay);
            oneStep();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent prevIntent = getIntent();
        setFast(prevIntent.getBooleanExtra(LEVEL,false));
        setButton(prevIntent.getBooleanExtra(BUTTON, false));

        gameLogic = new GameLogic();

        findViews();
        glideImpl();
        matrixStart();
        initControls();
        initSettings(getIsButton());
        startRunnable();


    }


    public void glideImpl(){
        Glide
                .with(this)
                .load(R.drawable.ic_background_lettuce)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(main_IMG_background);
    }

    public void oneStep(){
        gameLogic.oneStepOnion();
        if(gameLogic.onionInSalad){
            signalOnHit();
            gameLogic.onionInSalad = false;
        }
        gameLogic.changeScore(1);
        refreshUi();
        checkGameOver();
    }

    private void checkGameOver(){
        if(gameLogic.isGameOver()){
            doGameOver();
        }
    }

    private void stopRunnable(){
        h.removeCallbacks(runnable);
    }
    private void startRunnable(){
        h.postDelayed(runnable,2000);
    }
    protected void onResume() { //@@not working
        super.onResume();
        if(isPaused){
            h.postDelayed(runnable,delay+1000);
            if(!isButton)
                sensorsDetector.start();
            isPaused = false;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
        stopRunnable();
        sensorsDetector.stop();
    }

    private void initControls(){
        playerDirections();
        initSensorDetection();
    }

    private void initSettings(boolean isButton){
        isPaused = false;
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

        main_IMG_background = findViewById(R.id.main_IMG_background);

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

    public void doGameOver(){
        stopRunnable();
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
        SignalGenerator.getInstance().getCrashSound().start();
    }

    public void signalOnHit(){
        playCrashSound();
        vibrate();
        toast("Disgusting.");
    }

    public void vibrate(){
        SignalGenerator.getInstance().vibrate();
    }
    public void toast(String str){
        SignalGenerator.getInstance().toast(str);
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
                    toast("Fast");
                }
            }

            @Override
            public void moveForw() {
                if(delay != SLOW_MODE){
                    changeDelay(SLOW_MODE);
                    toast("Slow");
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
        GOIntent.putExtra(GameOverActivity.SCORE,gameLogic.getScore()); // to know the score of the user
        startActivity(GOIntent);
        finish();
    }

}
