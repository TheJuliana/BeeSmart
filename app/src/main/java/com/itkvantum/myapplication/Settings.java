package com.itkvantum.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Settings extends AppCompatActivity {
    Dialog about_me;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_about);
        dialog.setCancelable(false);
        Button about_me = findViewById(R.id.about_me);
        about_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.show();
                } catch (Exception e){
                    //empty
                }
                TextView info = dialog.findViewById(R.id.ab);
                info.setText(R.string.about);
                Button back = dialog.findViewById(R.id.btn_back_to_start);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            dialog.dismiss();

                        } catch (Exception e) {
                            //
                        }
                        dialog.dismiss();
                    }
                });
            }
        }
        );


        //включить музыку
        Button btn_mus = findViewById(R.id.btn_mus);
        btn_mus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 9, 9);
            }
        });

        //выключить музыку
        Button btn_no_mus = findViewById(R.id.but_no_mus);
        btn_no_mus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            }
        });

        // назад в меню
        Button btn_back = findViewById(R.id.btn_back_to_start);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Settings.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    // empty
                }
            }
        });
    }
}
