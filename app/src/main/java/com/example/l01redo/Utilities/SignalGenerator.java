package com.example.l01redo.Utilities;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

import com.example.l01redo.R;

public class SignalGenerator {

   private static Vibrator v;

   private static MediaPlayer mediaPlayerWubba;

   private static SignalGenerator instance = null;
   private Context c;

   public static SignalGenerator getInstance(){
      return instance;
   }
   public SignalGenerator(Context con){
      this.c = con;
   }

   public void vibrate(){

      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
         v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
      }
      else {
         v.vibrate(500);
      }
   }

   public void toast(String s) {
      Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
   }

   public static void initSG(Context con){
      if(instance == null){
         instance = new SignalGenerator(con);
      }
      v = (Vibrator) con.getSystemService(Context.VIBRATOR_SERVICE);
      mediaPlayerWubba = MediaPlayer.create(con,R.raw.wubba);
   }

   public MediaPlayer getCrashSound() {
      return mediaPlayerWubba;
   }
}
