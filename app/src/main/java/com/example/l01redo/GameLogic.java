package com.example.l01redo;

public class GameLogic {

     //variables and finals area, can be changed by prog;

     private final int TOTAL_PUKE = 3;
     private int pukeLeft;

     private boolean gameOver = false;

     int currSalad;

     public boolean onionInSalad = false;

     public boolean isGameOver() { // on the next assignment it will be used - if game over - end;
          return gameOver;
     }

     public void setGameOver(boolean gameOver) {
          this.gameOver = gameOver;
     }

     private final int ROWS = 6;
     private final int COLS = 3;


     enum state{
          EMPTY,ONION,SALAD
     }
     private state[][] mat;

     enum direction{
          LEFT,RIGHT
     }




     //initializing for first time;
     public GameLogic(){
          int currSalad = 1;
          pukeLeft = TOTAL_PUKE;
          setMat();
     }


     //getters and setters;

     public int getTOTAL_PUKE() {
          return TOTAL_PUKE;
     }

     public int getPukeLeft() {
          return pukeLeft;
     }

     public void setPukeLeft() {
          this.pukeLeft--;
          if(getPukeLeft() > TOTAL_PUKE)
               setGameOver(true);
     }

     public int getROWS() {
          return ROWS;
     }

     public int getCOLS() {
          return COLS;
     }

     public state[][] getMat() {
          return mat;
     }

     public void setMat() {
          mat = new state[ROWS][COLS];

          currSalad = 1; // deafult at start

          for (int rows = 0; rows < getROWS()-1 ; rows++) {
               for (int cols = 0; cols < getCOLS(); cols++) {
                    getMat()[rows][cols] = state.EMPTY;
               }
          }
          getMat()[getROWS()-1][0] = state.EMPTY;
          getMat()[getROWS()-1][1] = state.SALAD;
          getMat()[getROWS()-1][2] = state.EMPTY;

          this.mat = mat;
     }

     //onions functions

     private void randomOnionAppear() {
          int col = (int)(Math.random() * ((getCOLS()-1) + 1));
          getMat()[0][col] = state.ONION;
     }

     public void oneStepOnion(){
          for (int i = getROWS()-2; i >= 0 ; i--) {
               for (int j = 0; j < getCOLS(); j++) {
                    if(getMat()[i][j] == state.ONION){
                         if((i == getROWS()-2 ) && (getMat()[getROWS()-1][j] == state.SALAD)){
                              setPukeLeft();
                              onionInSalad = true;
                             getMat()[i][j] = state.EMPTY;
                         }
                         else if((i == getROWS()-2 ) && (getMat()[getROWS()-1][j] == state.EMPTY)){
                              getMat()[i][j] = state.EMPTY;
                         }
                         else{
                              getMat()[i][j] = state.EMPTY;
                              getMat()[i+1][j] = state.ONION;
                         }
                    }
               }
          }
          randomOnionAppear();
     }

     //salad functions

     public void changeSaladPos(direction direct){
          //for the next assignment not necessarily 0,1,2 but changeable

          if (currSalad == 0){
               if (direct == direction.RIGHT){
                    getMat()[getROWS()-1][0] = state.EMPTY;
                    getMat()[getROWS()-1][1]=state.SALAD;
                    currSalad = 1;
                    return;
               }
               return;
          }
          if (currSalad == 2){
               if (direct == direction.LEFT){
                    getMat()[getROWS()-1][2] = state.EMPTY;
                    getMat()[getROWS()-1][1] = state.SALAD;
                    currSalad = 1;
                    return;
               }
               return;
          }
          if (currSalad == 1){
               if (direct == direction.LEFT){
                    getMat()[getROWS()-1][1] = state.EMPTY;
                    getMat()[getROWS()-1][0] = state.SALAD;
                    currSalad = 0;
                    return;
               }
               //else if (btn == findViewById(R.id.main_FAB_right)) {
               else if (direct == direction.RIGHT) {
                    getMat()[getROWS() - 1][1] = state.EMPTY;
                    getMat()[getROWS() - 1][2] = state.SALAD;
                    currSalad = 2;
                    return;
               }
               return;
          }
     }


}



