package com.example.l01redo.ActivitiesAndLogic;

public class GameLogic {

     //variables and finals area, can be changed by prog;

     private final int TOTAL_PUKE = 3;
     private int pukeLeft;
     private int score;

     private boolean gameOver = false;

     int currSalad;

     public boolean onionInSalad = false;

     public boolean isGameOver() {
          return gameOver;
     }

     public void setGameOver(boolean gameOver) {
          this.gameOver = gameOver;
     }

     private final int ROWS = 8;
     private final int COLS = 5;


     enum state{
          EMPTY,ONION,SALAD,TUNA
     }
     private state[][] mat;

     enum direction{
          LEFT,RIGHT
     }




     //initializing for first time;
     public GameLogic(){
          pukeLeft = TOTAL_PUKE;
          score = 0;
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
          if(getPukeLeft() <= 0)
               setGameOver(true);
     }

     public int getScore(){
          return score;
     }

     public void changeScore(int num){
          score += num;
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

          currSalad = 2; // deafult at start

          for (int rows = 0; rows < getROWS()-1 ; rows++) {
               for (int cols = 0; cols < getCOLS(); cols++) {
                    getMat()[rows][cols] = state.EMPTY;
               }
          }
          getMat()[getROWS()-1][0] = state.EMPTY;
          getMat()[getROWS()-1][1] = state.EMPTY;
          getMat()[getROWS()-1][2] = state.SALAD;
          getMat()[getROWS()-1][3] = state.EMPTY;
          getMat()[getROWS()-1][4] = state.EMPTY;

          this.mat = mat;
     }

     //onions functions

     private void randomOnionAppear() {
          int col = (int)(Math.random() * ((getCOLS()-1) + 1));
          int willTuna = (int)(Math.random() * (4));
          int tunaCol = (int)(Math.random() * ((getCOLS()-1) + 1));

          getMat()[0][col] = state.ONION;
          if((tunaCol != col) && (willTuna == 2)){
               getMat()[0][tunaCol] = state.TUNA;
          }

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
                    else if(getMat()[i][j] == state.TUNA){
                         if((i == getROWS()-2 ) && (getMat()[getROWS()-1][j] == state.SALAD)){
                              changeScore(10);
                              getMat()[i][j] = state.EMPTY;
                         }
                         else if((i == getROWS()-2 ) && (getMat()[getROWS()-1][j] == state.EMPTY)){
                              getMat()[i][j] = state.EMPTY;
                         }
                         else{
                              getMat()[i][j] = state.EMPTY;
                              getMat()[i+1][j] = state.TUNA;
                         }
                    }
               }
          }
          if(!isGameOver()){
               randomOnionAppear();
          }
     }

     //salad functions

     public void changeSaladPos(direction direct){

          if (currSalad == 0){
               if (direct == direction.RIGHT){
                    getMat()[getROWS()-1][0] = state.EMPTY;
                    getMat()[getROWS()-1][1]=state.SALAD;
                    currSalad = 1;
               }
          }



          else if (currSalad == 4){ //5
               if (direct == direction.LEFT){
                    getMat()[getROWS()-1][4] = state.EMPTY; //5
                    getMat()[getROWS()-1][3] = state.SALAD; //4
                    currSalad = 3;
               }
          }


         else if (currSalad == 2){
               if (direct == direction.LEFT){
                    getMat()[getROWS()-1][2] = state.EMPTY;
                    getMat()[getROWS()-1][1] = state.SALAD;
                    currSalad = 1;

               }
               else if (direct == direction.RIGHT) {
                    getMat()[getROWS() - 1][2] = state.EMPTY;
                    getMat()[getROWS() - 1][3] = state.SALAD;
                    currSalad = 3;

               }

          }



         else if (currSalad == 1){
               if (direct == direction.LEFT){
                    getMat()[getROWS()-1][1] = state.EMPTY;
                    getMat()[getROWS()-1][0] = state.SALAD;
                    currSalad = 0;

               }

               else if (direct == direction.RIGHT) {
                    getMat()[getROWS() - 1][1] = state.EMPTY;
                    getMat()[getROWS() - 1][2] = state.SALAD;
                    currSalad = 2;

               }

          }



          else if (currSalad == 3){
               if (direct == direction.LEFT){
                    getMat()[getROWS()-1][3] = state.EMPTY;
                    getMat()[getROWS()-1][2] = state.SALAD;
                    currSalad = 2;

               }

               else if (direct == direction.RIGHT) {
                    getMat()[getROWS() - 1][3] = state.EMPTY;
                    getMat()[getROWS() - 1][4] = state.SALAD;
                    currSalad = 4;

               }

          }


     }


}



