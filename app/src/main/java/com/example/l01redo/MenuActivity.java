package com.example.l01redo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuActivity extends AppCompatActivity {

    MaterialButton[] buttonModeArrBtn;
    MaterialButton sensorModeBtn;
    MaterialButton startBtn;
    MaterialButton scoreBtn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }




    private void findViews(){
        buttonModeArrBtn = new MaterialButton[]{
                findViewById(R.id.menu_BTN_slow), findViewById(R.id.menu_BTN_fast)
        };

        sensorModeBtn = findViewById(R.id.menu_BTN_sensors);

        startBtn = findViewById(R.id.menu_BTN_start);

        scoreBtn = findViewById(R.id.menu_BTN_score);

    }

    private void buttonModeClick(){

        for (MaterialButton btn: buttonModeArrBtn) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btn == findViewById(R.id.menu_BTN_slow)){


                    }
                    else if (btn == findViewById(R.id.menu_BTN_fast)){

                    }
                }
            });

        }


    }





}
