package com.example.l01redo.Utilities;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

import com.example.l01redo.R;

public class SignalGenerator {

   private static SignalGenerator instance = null;
   private Context context;
   private static Vibrator v;
   private static MediaPlayer mediaPlayerWubba;
   private SignalGenerator(Context context){
      this.context = context;
   }
   public static void initSG(Context context){
      if(instance == null){
         instance = new SignalGenerator(context);
         v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
         mediaPlayerWubba = MediaPlayer.create(context,R.raw.wubba);
      }
   }

   public static SignalGenerator getInstance(){
      return instance;
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
      Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
   }


   public MediaPlayer getCrashSound() {
      return mediaPlayerWubba;
   }
}
