package com.itkvantum.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long timeBackPressed;
    private Toast toast;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, MyService.class));
//        private void onBackPressed() {
//            stopService(new Intent(this, MyService.class));
//        }

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {
                    //empty
                }
            }
        });

        Button btn_settings = (Button)findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, Settings.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    //empty
                }
            }

        });
    }

    @Override
    public void onBackPressed() {
        if (timeBackPressed + 2000 > System.currentTimeMillis())
        {
            super.onBackPressed();
            toast.cancel();
        } else {
            toast = Toast.makeText(getBaseContext(), "Нажмите ещё раз для выхода из приложения", Toast.LENGTH_SHORT);
            toast.show();
        }
        stopService(new Intent(this, MyService.class));
        timeBackPressed = System.currentTimeMillis();
    }
}