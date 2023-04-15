package com.example.l01redo;

public class GameLogic {

     //variables and finals area, can be changed by prog;

     private final int TOTAL_PUKE = 3;
     private int pukeUsed;
     private int pukeLeft;
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
     public GameLogic(state[][] mat, final int TOTAL_PUKE,final int ROWS, final int COLS){
          pukeUsed = 0;
          setPukeLeft();
          setMat(mat,ROWS,COLS);
          getMat()[getROWS()-1][getCOLS()/2] = state.SALAD; //need to change it if ill
          // allow even number of cols;
     }


     //getters and setters;

     public int getTOTAL_PUKE() {
          return TOTAL_PUKE;
     }

     public int getPukeUsed() {
          return pukeUsed;
     }

     public void setPukeUsed(){ //on the next assignment, if puke used > total = end game
          this.pukeUsed++;
     }

     public int getPukeLeft() {
          return pukeLeft;
     }

     public void setPukeLeft() {
          this.pukeLeft = this.getTOTAL_PUKE()-this.getPukeUsed();
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

     public void setMat(state[][] mat, int rows, int cols) {
          mat = new state[ROWS][COLS];
          for (int i = 0; i < ROWS; i++) {
               for (int j = 0; j < COLS; j++) {
                    mat[i][j] = state.EMPTY;
               }
          }
          this.mat = mat;
     }



     //onions functions

     private void randomOnionAppear(state[][] matrix) {
          int col = (int)(Math.random() * ((getCOLS()-1) + 1));
          getMat()[0][col] = state.ONION;
     } //should be used on the first time only, and from there only in oneStepOnion;

     private void oneStepOnion(state[][] matrix){
          for (int i = getROWS()-2; i >=0 ; i--) {
               for (int j = 0; j < getCOLS(); j++) {
                    if(getMat()[i][j] == state.ONION){
                         if (i == getROWS()-2){
                              if(getMat()[getROWS()-1][j] == state.SALAD){
                                   setPukeUsed();
                                   setPukeLeft();
                              }
                              getMat()[i][j] = state.EMPTY;
                         }

                         else{
                              getMat()[i][j] = state.EMPTY;
                              getMat()[i+1][j] = state.ONION;
                         }
                    }
               }
          }
          randomOnionAppear(matrix);
     }

     //salad functions

     private void changeSaladPos(state[][] matrix, direction direct){
          if(getMat()[getROWS()-1][1] == state.SALAD){ //for the next assignment not necessarily 0,1,2 but changeable
               if (direct == direction.LEFT){
                    getMat()[getROWS()-1][0] = state.SALAD;
                    getMat()[getROWS()-1][1] = state.EMPTY;
               }
               else if (direct == direction.RIGHT){
                    getMat()[getROWS()-1][2] = state.SALAD;
                    getMat()[getROWS()-1][1] = state.EMPTY;
               }
               else
                    return;
          }
          else if(getMat()[getROWS()-1][0] == state.SALAD){
               if (direct == direction.RIGHT) {
                    getMat()[getROWS() - 1][1] = state.SALAD;
                    getMat()[getROWS() - 1][0] = state.EMPTY;
               }
               else
                    return;
          }
          else if(getMat()[getROWS()-1][2] == state.SALAD){
               if (direct == direction.LEFT) {
                    getMat()[getROWS() - 1][1] = state.SALAD;
                    getMat()[getROWS() - 1][2] = state.EMPTY;
               }
               else
                    return;
          }
          else
               return;
     }


}



