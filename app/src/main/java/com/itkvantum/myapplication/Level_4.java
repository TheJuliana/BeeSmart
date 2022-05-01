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

import java.util.ArrayList;
import java.util.Random;

public class Level_4 extends AppCompatActivity {

    private long timeBackPressed;
    private Toast toast;
    Dialog dialog;
    Dialog dialog_result;
    Dialog dialog_menu;


    boolean chooseCard = false;
    int flag = 0;

    int numLeft = 0;
    int numRight = 0;
    int numLeft_past = 0;
    int count = 0;
    int countTrue = 0;
    int length = 10;
    Array array = new Array();
    public ArrayList<Integer> imgLeftInUse = new ArrayList<>();
    public ArrayList<Integer> imgRightInUse = new ArrayList<>();

    private static boolean isLvlDone  = false;
    public static boolean getIsLvlDone() {
        return isLvlDone;
    }

    Random random = new Random();

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
                    Intent intent = new Intent(Level_4.this, GameLevels.class);
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
                    Intent intent = new Intent(Level_4.this, Level_5.class);
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
                            Intent intent = new Intent(Level_4.this, GameLevels.class);
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
        dialog.setContentView(R.layout.dialog3);
        dialog.setCancelable(false);
        TextView btn_dialog = (TextView) dialog.findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

        ImageView btn_left = (ImageView) findViewById(R.id.btn_left);
        ImageView btn_right = (ImageView) findViewById(R.id.btn_right);
        TextView name_lvl = (TextView) findViewById(R.id.name_lvl);
        name_lvl.setText(R.string.level_4);
        TextView lvl_desc = (TextView) findViewById(R.id.lvl_description);
        final Animation animation = AnimationUtils.loadAnimation(Level_4.this, R.anim.alpha);

        dialog_result = new Dialog(this);
        dialog_result.setContentView(R.layout.dialog_result);
        dialog_result.setCancelable(false);

        numLeft = random.nextInt(length);
        numLeft_past = numLeft;
        while (numLeft_past == numLeft) {
            numLeft = random.nextInt(length);
        }

        lvl_desc.setText(array.text4[numLeft]);
        numRight = random.nextInt(length);
        while (numLeft == numRight) {
            numRight = random.nextInt(length);
        }

        chooseCard = random.nextBoolean();
        if (chooseCard) {
            btn_left.setImageResource(array.images4[numLeft]);
            btn_right.setImageResource(array.images4[numRight]);
            flag = 1;
        } else {
            btn_left.setImageResource(array.images4[numRight]);
            btn_right.setImageResource(array.images4[numLeft]);
            flag = 2;
        }

        imgLeftInUse.add(numLeft);
        imgRightInUse.add(numRight);

        final int points[] = {R.id.point_1, R.id.point_2, R.id.point_3, R.id.point_4, R.id.point_5,
                R.id.point_6, R.id.point_7, R.id.point_8, R.id.point_9, R.id.point_10 };

        btn_left.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_right.setEnabled(false);
                    if (flag == 1) {
                        btn_left.setImageResource(R.drawable.answ_true_2);
                    } else {
                        btn_left.setImageResource(R.drawable.answ_false_2);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (flag == 1) {
                        if (count < 10) {
                            count++;
                            countTrue++;
                        }
                        TextView point = findViewById(points[count - 1]);
                        point.setBackgroundResource(R.drawable.point_answ_true);
                    } else {
                        if (count < 10) {
                            count++;
                        }
                        TextView point = findViewById(points[count - 1]);
                        point.setBackgroundResource(R.drawable.point_answ_false);
                    }

                    if (count == 10) {
                        isLvlDone = true;
                        //выход из уровня
                        TextView txt_result = dialog_result.findViewById(R.id.txt_result);
                        txt_result.setText(getResources().getString(R.string.results) + " " + countTrue);

                        ImageView img_result = dialog_result.findViewById(R.id.img_result);
                        if (countTrue <= 3) {
                            img_result.setImageResource(R.drawable.result_bad);
                        } else if (countTrue <= 9) {
                            img_result.setImageResource(R.drawable.result_normal);
                        } else if (countTrue == 10) {
                            img_result.setImageResource(R.drawable.result_cool);
                        }

                        Button btn_result = dialog_result.findViewById(R.id.btn_dialog);
                        btn_result.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Intent intent = new Intent(Level_4.this, Level_5.class);
                                    startActivity(intent);
                                    finish();
                                } catch (Exception e) {
                                    // empty
                                }
                                dialog_result.dismiss();
                            }
                        });
                        dialog_result.show();



                    } else {
                        numLeft = random.nextInt(length);
                        while (imgLeftInUse.contains(numLeft)) {
                            numLeft = random.nextInt(length);
                        }

                        lvl_desc.setText(array.text4[numLeft]);
                        numRight = random.nextInt(length);
                        while (numLeft == numRight || imgRightInUse.contains(numRight)) {
                            numRight = random.nextInt(length);
                        }

                        chooseCard = random.nextBoolean();
                        if (chooseCard) {
                            btn_left.setImageResource(array.images4[numLeft]);
                            btn_right.setImageResource(array.images4[numRight]);
                            flag = 1;
                        } else {
                            btn_left.setImageResource(array.images4[numRight]);
                            btn_right.setImageResource(array.images4[numLeft]);
                            flag = 2;
                        }
                        btn_right.setEnabled(true);
                        btn_left.startAnimation(animation);
                        btn_right.startAnimation(animation);
                        imgLeftInUse.add(numLeft);
                        imgRightInUse.add(numRight);
                    }
                }
                return true;
            }
        });

        btn_right.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_left.setEnabled(false);
                    if (flag == 2) {
                        btn_right.setImageResource(R.drawable.answ_true_2);
                    } else {
                        btn_right.setImageResource(R.drawable.answ_false_2);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (flag == 2) {
                        if (count < 10) {
                            count++;
                            countTrue++;
                        }
                        TextView point = findViewById(points[count - 1]);
                        point.setBackgroundResource(R.drawable.point_answ_true);
                    } else {
                        if (count < 10) {
                            count++;
                        }
                        TextView point = findViewById(points[count - 1]);
                        point.setBackgroundResource(R.drawable.point_answ_false);
                    }

                    if (count == 10) {
                        isLvlDone = true;
                        //выход из уровня
                        TextView txt_result = dialog_result.findViewById(R.id.txt_result);
                        txt_result.setText(getResources().getString(R.string.results) + " " + countTrue);

                        ImageView img_result = dialog_result.findViewById(R.id.img_result);
                        if (countTrue <= 3) {
                            img_result.setImageResource(R.drawable.result_bad);
                        } else if (countTrue <= 9) {
                            img_result.setImageResource(R.drawable.result_normal);
                        } else if (countTrue == 10) {
                            img_result.setImageResource(R.drawable.result_cool);
                        }

                        Button btn_result = dialog_result.findViewById(R.id.btn_dialog);
                        btn_result.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Intent intent = new Intent(Level_4.this, Level_5.class);
                                    startActivity(intent);
                                    finish();
                                } catch (Exception e) {
                                    // empty
                                }
                                dialog_result.dismiss();
                            }
                        });
                        dialog_result.show();

                    } else {
                        numLeft = random.nextInt(length);
                        while (imgLeftInUse.contains(numLeft)) {
                            numLeft = random.nextInt(length);
                        }

                        lvl_desc.setText(array.text4[numLeft]);
                        //lvl_desc.setText(array.text2[numLeft]);
                        numRight = random.nextInt(length);
                        while (numLeft == numRight || imgRightInUse.contains(numRight)) {
                            numRight = random.nextInt(length);
                        }

                        chooseCard = random.nextBoolean();
                        if (chooseCard) {
                            btn_left.setImageResource(array.images4[numLeft]);
                            btn_right.setImageResource(array.images4[numRight]);
                            flag = 1;
                        } else {
                            btn_left.setImageResource(array.images4[numRight]);
                            btn_right.setImageResource(array.images4[numLeft]);
                            flag = 2;
                        }
                        btn_left.setEnabled(true);
                        btn_left.startAnimation(animation);
                        btn_right.startAnimation(animation);
                        imgLeftInUse.add(numLeft);
                        imgRightInUse.add(numRight);
                    }
                }
                return true;
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