package com.itkvantum.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Level_7 extends AppCompatActivity {

    private long timeBackPressed;
    private Toast toast;
    Dialog dialog;
    Dialog dialog_result;
    Dialog dialog_menu;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //кнопка назад
        TextView btn_prev = (TextView) findViewById(R.id.btn_prev);
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level_7.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    // empty
                }
            }
        });

        //кнопка вперед
        TextView btn_next = (TextView) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level_7.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    // empty
                }
            }
        });

        dialog_menu = new Dialog(this);
        dialog_menu.setContentView(R.layout.dialog_menu);
        dialog_menu.setCancelable(false);

        // кнопка меню
        TextView btn_to_menu = findViewById(R.id.btn_to_menu);
        btn_to_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Button btn_dialog_cont = dialog_menu.findViewById(R.id.btn_dialog);
                    btn_dialog_cont.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog_menu.dismiss();
                        }
                    });

                    Button btn_to_lvl = dialog_menu.findViewById(R.id.btn_to_lvl);
                    btn_to_lvl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Level_7.this, GameLevels.class);
                            startActivity(intent);
                            finish();
                            dialog_menu.dismiss();
                        }
                    });

                    Button btn_to_exit = dialog_menu.findViewById(R.id.btn_to_exit);
                    btn_to_exit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finishAffinity();
                        }
                    });

                } catch (Exception e) {
                    // empty
                }

                dialog_menu.show();
            }

        });


        dialog = new Dialog(this);
        dialog.show();

        TextView name_lvl = (TextView) findViewById(R.id.name_lvl);
        name_lvl.setText(R.string.level_7);
        TextView lvl_desc = (TextView) findViewById(R.id.lvl_description);
        lvl_desc.setText(R.string.cs7);

        final Animation animation = AnimationUtils.loadAnimation(Level_7.this, R.anim.alpha);

        dialog.show();
    }
        @Override
        public void onBackPressed () {
            if (timeBackPressed + 2000 > System.currentTimeMillis()) {
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


