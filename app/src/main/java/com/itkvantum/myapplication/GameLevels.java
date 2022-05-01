package com.itkvantum.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    private long timeBackPressed;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_levels);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //???????
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_result);
        dialog.setCancelable(false);

        TextView txt = dialog.findViewById(R.id.txt_result);
        txt.setText(R.string.game_over);

        if (Level_1.getIsLvlDone() && Level_2.getIsLvlDone() && Level_3.getIsLvlDone()
                && Level_4.getIsLvlDone() && Level_5.getIsLvlDone() && Level_6.getIsLvlDone()) {
            TextView btn = dialog.findViewById(R.id.btn_dialog);
            btn.setOnClickListener((View v) -> dialog.dismiss());
            dialog.show();
        }

        Button btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {
                    // пусто
                }
            }
        });

        TextView btn_lvl_1 = (TextView)findViewById(R.id.btn_lvl_1);
        btn_lvl_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level_1.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {
                    // пусто
                }
            }
        });

        TextView btn_lvl_2 = (TextView)findViewById(R.id.btn_lvl_2);
        btn_lvl_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level_2.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {
                    // пусто
                }
            }
        });

        TextView btn_lvl_3 = (TextView)findViewById(R.id.btn_lvl_3);
        btn_lvl_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level_3.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {
                    // пусто
                }
            }
        });

        TextView btn_lvl_4 = (TextView)findViewById(R.id.btn_lvl_4);
        btn_lvl_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level_4.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {
                    // пусто
                }
            }
        });

        TextView btn_lvl_5 = (TextView)findViewById(R.id.btn_lvl_5);
        btn_lvl_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level_5.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {
                    // пусто
                }
            }
        });

        TextView btn_lvl_6 = (TextView)findViewById(R.id.btn_lvl_6);
        btn_lvl_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level_6.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {
                    // пусто
                }
            }
        });

        TextView btn_lvl_7 = (TextView)findViewById(R.id.btn_lvl_7);
        btn_lvl_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level_7.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e) {
                    // пусто
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