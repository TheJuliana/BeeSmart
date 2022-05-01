package com.itkvantum.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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

//import kotlin._Assertions;

public class Level_6 extends AppCompatActivity {

    private long timeBackPressed;
    private Toast toast;
    private Dialog dialog_menu;
    private Dialog dialog_result;

    private int numLeft_1 = 0;
    private int numLeft_2 = 0;
    private int numLeft_3 = 0;
    private int numRight_1 = 0;
    private int numRight_2 = 0;
    private int numRight_3 = 0;
    private int chooseCardLeft = 6; // выбор карточки
    private int chooseCardRight = 6;
    private int cardCount = 3;
    private int flagLeft = 0; // флаг
    private int flagRight = 0;
    private int flagChooseLeft = 0;
    private int flagChooseRight = 0;
    private int count = 0; // кол-во ответов всего
    private static int countTrue = 0; // кол-во верных ответов
    private int countTrueAll = 0;
    private int answersCount = 10;
    private boolean isBtnLeft_1Touched = false;
    private boolean isBtnLeft_2Touched = false;
    private boolean isBtnLeft_3Touched = false;
    private boolean isBtnRight_1Touched = false;
    private boolean isBtnRight_2Touched = false;
    private boolean isBtnRight_3Touched = false;

    private static boolean isLvlDone = false;

    public static boolean getIsLvlDone() {
        return isLvlDone;
    }


    private int indexLeft = 0;
    private int indexRight = 0;

    private Array array = new Array();
    private ArrayList<Integer> imgLeftInUse = new ArrayList<>();
    private ArrayList<Integer> imgRightInUse = new ArrayList<>();
    private Random random = new Random();

    final int points[] = {R.id.point_1, R.id.point_2, R.id.point_3, R.id.point_4, R.id.point_5,
            R.id.point_6, R.id.point_7, R.id.point_8, R.id.point_9, R.id.point_10};

    private static boolean isUse = false;

    public static boolean getInUse() {
        return isUse;
    }

    public static int getCountTrue() {
        return countTrue;
    }

    public static void setCountTrue(int count) {
        countTrue = count;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal_parallel);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView lvl_name = findViewById(R.id.name_lvl);
        lvl_name.setText(R.string.level_6);

        //кнопка назад
        TextView btn_prev = (TextView) findViewById(R.id.btn_prev);
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level_6.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                    isUse = true;
                } catch (Exception e) {
                    // empty
                }
            }
        });

        // кнопка вперёд
        TextView btn_next = (TextView) findViewById(R.id.brn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level_6.this, Level_7.class);
                    startActivity(intent);
                    finish();
                    isUse = true;
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
                            Intent intent = new Intent(Level_6.this, Level_6.class);
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

        dialog_result = new Dialog(this);
        dialog_result.setContentView(R.layout.dialog_result);
        dialog_result.setCancelable(false);
        TextView txt_result = dialog_result.findViewById(R.id.txt_result);
        ImageView img_result = dialog_result.findViewById(R.id.img_result);
        txt_result.setText(R.string.rules_4);
        img_result.setImageResource(R.drawable.pic_dialog);

        TextView btn_result = (TextView) dialog_result.findViewById(R.id.btn_dialog);
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_result.dismiss();
            }
        });
        dialog_result.show();

        TextView btn_left_1 = findViewById(R.id.btn_left_1);
        TextView btn_left_2 = findViewById(R.id.btn_left_2);
        TextView btn_left_3 = findViewById(R.id.btn_left_3);
        TextView btn_right_1 = findViewById(R.id.btn_right_1);
        TextView btn_right_2 = findViewById(R.id.btn_right_2);
        TextView btn_right_3 = findViewById(R.id.btn_right_3);

        numLeft_1 = random.nextInt(array.img_6.length);
        while (numLeft_1 == numLeft_2) {
            numLeft_2 = random.nextInt(array.img_6.length);
        }
        while (numLeft_2 == numLeft_3 || numLeft_1 == numLeft_3) {
            numLeft_3 = random.nextInt(array.img_6.length);
        }

        numRight_1 = numLeft_1;
        numRight_2 = numLeft_2;
        numRight_3 = numLeft_3;

        chooseCardLeft = random.nextInt(cardCount);
        if (chooseCardLeft == 0) {
            btn_left_1.setText(array.img_6[numLeft_1]);
            btn_left_2.setText(array.img_6[numLeft_2]);
            btn_left_3.setText(array.img_6[numLeft_3]);
            flagLeft = 0;
        } else if (chooseCardLeft == 1) {
            btn_left_1.setText(array.img_6[numLeft_1]);
            btn_left_2.setText(array.img_6[numLeft_3]);
            btn_left_3.setText(array.img_6[numLeft_2]);
            flagLeft = 1;
        } else if (chooseCardLeft == 2) {
            btn_left_1.setText(array.img_6[numLeft_2]);
            btn_left_2.setText(array.img_6[numLeft_1]);
            btn_left_3.setText(array.img_6[numLeft_3]);
            flagLeft = 2;
        } else if (chooseCardLeft == 3) {
            btn_left_1.setText(array.img_6[numLeft_2]);
            btn_left_2.setText(array.img_6[numLeft_3]);
            btn_left_3.setText(array.img_6[numLeft_1]);
            flagLeft = 3;
        } else if (chooseCardLeft == 4) {
            btn_left_1.setText(array.img_6[numLeft_3]);
            btn_left_2.setText(array.img_6[numLeft_1]);
            btn_left_3.setText(array.img_6[numLeft_2]);
            flagLeft = 4;
        } else if (chooseCardLeft == 5) {
            btn_left_1.setText(array.img_6[numLeft_3]);
            btn_left_2.setText(array.img_6[numLeft_2]);
            btn_left_3.setText(array.img_6[numLeft_1]);
            flagLeft = 5;
        }

        chooseCardRight = random.nextInt(cardCount);
        if (chooseCardRight == 0) {
            flagRight = 0;
            btn_right_1.setText(array.text_6[numRight_1]);
            btn_right_2.setText(array.text_6[numRight_2]);
            btn_right_3.setText(array.text_6[numRight_3]);
        } else if (chooseCardRight == 1) {
            flagRight = 1;
            btn_right_1.setText(array.text_6[numRight_1]);
            btn_right_2.setText(array.text_6[numRight_3]);
            btn_right_3.setText(array.text_6[numRight_2]);
        } else if (chooseCardRight == 2) {
            flagRight = 2;
            btn_right_1.setText(array.text_6[numRight_2]);
            btn_right_2.setText(array.text_6[numRight_1]);
            btn_right_3.setText(array.text_6[numRight_3]);
        } else if (chooseCardRight == 3) {
            flagRight = 3;
            btn_right_1.setText(array.text_6[numRight_2]);
            btn_right_2.setText(array.text_6[numRight_3]);
            btn_right_3.setText(array.text_6[numRight_1]);
        } else if (chooseCardRight == 4) {
            flagRight = 4;
            btn_right_1.setText(array.text_6[numRight_3]);
            btn_right_2.setText(array.text_6[numRight_1]);
            btn_right_3.setText(array.text_6[numRight_2]);
        } else if (chooseCardRight == 5) {
            flagRight = 5;
            btn_right_1.setText(array.text_6[numRight_3]);
            btn_right_2.setText(array.text_6[numRight_2]);
            btn_right_3.setText(array.text_6[numRight_1]);
        }

//        imgLeftInUse.add(numLeft_1);
//        imgLeftInUse.add(numLeft_2);
//        imgLeftInUse.add(numLeft_3);

        btn_right_1.setEnabled(false);
        btn_left_2.setEnabled(false);
        btn_left_3.setEnabled(false);

        btn_left_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_left_2.setEnabled(false);
                btn_left_3.setEnabled(false);
                btn_left_1.setBackgroundResource(R.drawable.choose_card_1);
                isBtnLeft_1Touched = true;

                btn_right_1.setEnabled(true);
                btn_right_2.setEnabled(true);
                btn_right_3.setEnabled(true);

                System.out.println("btn_left_1 is touched");
            }

        });

        btn_left_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_left_1.setEnabled(false);
                btn_left_3.setEnabled(false);
                btn_left_2.setBackgroundResource(R.drawable.choose_card_2);
                isBtnLeft_2Touched = true;

                btn_right_1.setEnabled(true);
                btn_right_2.setEnabled(true);
                btn_right_3.setEnabled(true);

                System.out.println("btn_left_2 is touched");
            }
        });

        btn_left_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_left_1.setEnabled(false);
                btn_left_2.setEnabled(false);
                btn_left_3.setBackgroundResource(R.drawable.choose_card_3);
                isBtnLeft_3Touched = true;

                btn_right_1.setEnabled(true);
                btn_right_2.setEnabled(true);
                btn_right_3.setEnabled(true);

                System.out.println("btn_left_3 is touched");
            }
        });

        btn_right_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_right_2.setEnabled(false);
                btn_right_3.setEnabled(false);
                if (isBtnLeft_1Touched) {
                    btn_right_1.setBackgroundResource(R.drawable.choose_card_1);

                    for (int i = 0; i < array.img_6.length; i++) {
                        if (getResources().getString(array.img_6[i]).contentEquals(btn_left_1.getText())) {
                            indexLeft = i;
                        }
                        if (getResources().getString(array.text_6[i]).contentEquals(btn_right_1.getText())) {
                            indexRight = i;
                        }
                    }

                    if (indexLeft == indexRight) {
                        countTrueAll++;
                    }

                    btn_left_1.setEnabled(false);
                    btn_left_2.setEnabled(true);
                    btn_left_3.setEnabled(true);
                    isBtnLeft_1Touched = false;

                } else if (isBtnLeft_2Touched) {
                    btn_right_1.setBackgroundResource(R.drawable.choose_card_2);

                    for (int i = 0; i < array.img_6.length; i++) {
                        if (getResources().getString(array.img_6[i]).contentEquals(btn_left_2.getText())) {
                            indexLeft = i;
                        }
                        if (getResources().getString(array.text_6[i]).contentEquals(btn_right_1.getText())) {
                            indexRight = i;
                        }
                    }

                    if (indexLeft == indexRight) {
                        countTrueAll++;
                    }

                    btn_left_1.setEnabled(true);
                    btn_left_2.setEnabled(false);
                    btn_left_3.setEnabled(true);
                    isBtnLeft_2Touched = false;

                } else if (isBtnLeft_3Touched) {
                    btn_right_1.setBackgroundResource(R.drawable.choose_card_3);

                    for (int i = 0; i < array.img_6.length; i++) {
                        if (getResources().getString(array.img_6[i]).contentEquals(btn_left_3.getText())) {
                            indexLeft = i;
                        }
                        if (getResources().getString(array.text_6[i]).contentEquals(btn_right_1.getText())) {
                            indexRight = i;
                        }
                    }

                    if (indexLeft == indexRight) {
                        countTrueAll++;
                    }

                    btn_left_1.setEnabled(true);
                    btn_left_2.setEnabled(true);
                    btn_left_3.setEnabled(false);
                    isBtnLeft_3Touched = false;
                }

                btn_right_1.setEnabled(false);
                btn_right_2.setEnabled(false);
                btn_right_3.setEnabled(false);
                isBtnRight_1Touched = true;
                flagChooseRight++;
                System.out.println("btn_right_1 is touched");
                isEnd(btn_left_1, btn_left_2, btn_left_3, btn_right_1, btn_right_2, btn_right_3);
            }
        });

        btn_right_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_right_1.setEnabled(false);
                btn_right_3.setEnabled(false);
                if (isBtnLeft_1Touched) {
                    btn_right_2.setBackgroundResource(R.drawable.choose_card_1);

                    for (int i = 0; i < array.img_6.length; i++) {
                        if (getResources().getString(array.img_6[i]).contentEquals(btn_left_1.getText())) {
                            indexLeft = i;
                        }
                        if (getResources().getString(array.text_6[i]).contentEquals(btn_right_2.getText())) {
                            indexRight = i;
                        }
                    }

                    if (indexLeft == indexRight) {
                        countTrueAll++;
                    }

                    btn_left_1.setEnabled(false);
                    btn_left_2.setEnabled(true);
                    btn_left_3.setEnabled(true);
                    isBtnLeft_1Touched = false;

                } else if (isBtnLeft_2Touched) {
                    btn_right_2.setBackgroundResource(R.drawable.choose_card_2);

                    for (int i = 0; i < array.img_6.length; i++) {
                        if (getResources().getString(array.img_6[i]).contentEquals(btn_left_2.getText())) {
                            indexLeft = i;
                        }
                        if (getResources().getString(array.text_6[i]).contentEquals(btn_right_2.getText())) {
                            indexRight = i;
                        }
                    }

                    if (indexLeft == indexRight) {
                        countTrueAll++;
                    }

                    btn_left_1.setEnabled(true);
                    btn_left_2.setEnabled(false);
                    btn_left_3.setEnabled(true);
                    isBtnLeft_2Touched = false;

                } else if (isBtnLeft_3Touched) {
                    btn_right_2.setBackgroundResource(R.drawable.choose_card_3);

                    for (int i = 0; i < array.img_6.length; i++) {
                        if (getResources().getString(array.img_6[i]).contentEquals(btn_left_3.getText())) {
                            indexLeft = i;
                        }
                        if (getResources().getString(array.text_6[i]).contentEquals(btn_right_2.getText())) {
                            indexRight = i;
                        }
                    }

                    if (indexLeft == indexRight) {
                        countTrueAll++;
                    }

                    btn_left_1.setEnabled(true);
                    btn_left_2.setEnabled(true);
                    btn_left_3.setEnabled(false);
                    isBtnLeft_3Touched = false;
                }

                flagChooseRight++;
                System.out.println("btn_right_2 is touched");
                btn_right_1.setEnabled(false);
                btn_right_2.setEnabled(false);
                btn_right_3.setEnabled(false);
                isBtnRight_2Touched = true;
                isEnd(btn_left_1, btn_left_2, btn_left_3, btn_right_1, btn_right_2, btn_right_3);
            }
        });

        btn_right_3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                btn_right_2.setEnabled(false);
                btn_right_1.setEnabled(false);
                if (isBtnLeft_1Touched) {
                    btn_right_3.setBackgroundResource(R.drawable.choose_card_1);

                    for (int i = 0; i < array.img_6.length; i++) {
                        if (getResources().getString(array.img_6[i]).contentEquals(btn_left_1.getText())) {
                            indexLeft = i;
                        }
                        if (getResources().getString(array.text_6[i]).contentEquals(btn_right_3.getText())) {
                            indexRight = i;
                        }
                    }

                    if (indexLeft == indexRight) {
                        countTrueAll++;
                    }

                    btn_left_1.setEnabled(false);
                    btn_left_2.setEnabled(true);
                    btn_left_3.setEnabled(true);
                    isBtnLeft_1Touched = false;

                } else if (isBtnLeft_2Touched) {
                    btn_right_3.setBackgroundResource(R.drawable.choose_card_2);

                    for (int i = 0; i < array.img_6.length; i++) {
                        if (getResources().getString(array.img_6[i]).contentEquals(btn_left_2.getText())) {
                            indexLeft = i;
                        }
                        if (getResources().getString(array.text_6[i]).contentEquals(btn_right_3.getText())) {
                            indexRight = i;
                        }
                    }

                    if (indexLeft == indexRight) {
                        countTrueAll++;
                    }

                    btn_left_1.setEnabled(true);
                    btn_left_2.setEnabled(false);
                    btn_left_3.setEnabled(true);
                    isBtnLeft_2Touched = false;

                } else if (isBtnLeft_3Touched) {
                    btn_right_3.setBackgroundResource(R.drawable.choose_card_3);

                    for (int i = 0; i < array.img_6.length; i++) {
                        if (getResources().getString(array.img_6[i]).contentEquals(btn_left_3.getText())) {
                            indexLeft = i;
                        }
                        if (getResources().getString(array.text_6[i]).contentEquals(btn_right_3.getText())) {
                            indexRight = i;
                        }
                    }

                    if (indexLeft == indexRight) {
                        countTrueAll++;
                    }

                    btn_left_1.setEnabled(true);
                    btn_left_2.setEnabled(true);
                    btn_left_3.setEnabled(false);
                    isBtnLeft_3Touched = false;
                }

                flagChooseRight++;
                System.out.println("btn_right_3 is touched");
                btn_right_3.setEnabled(false);
                btn_right_1.setEnabled(false);
                btn_right_2.setEnabled(false);
                isBtnRight_3Touched = true;
                isEnd(btn_left_1, btn_left_2, btn_left_3, btn_right_1, btn_right_2, btn_right_3);
            }
        });

    }

    public void isEnd(TextView btn_left_1, TextView btn_left_2, TextView btn_left_3, TextView btn_right_1, TextView btn_right_2, TextView btn_right_3) {
        if (flagChooseRight == 3) {
            btn_right_1.setEnabled(false);
            btn_right_2.setEnabled(false);
            btn_right_3.setEnabled(false);
            btn_left_1.setEnabled(false);
            btn_left_2.setEnabled(false);
            btn_left_3.setEnabled(false);
            if (countTrueAll == 3) {
                count++;
                countTrue++;
                TextView point = findViewById(points[count - 1]);
                point.setBackgroundResource(R.drawable.point_answ_true);
            } else {
                count++;
                TextView point = findViewById(points[count - 1]);
                point.setBackgroundResource(R.drawable.point_answ_false);
            }

            if (count == 10) {
                isLvlDone = true; // уровень пройден
                System.out.println("Lvl_6: " + isLvlDone);
                Intent intent = new Intent(Level_6.this, GameLevels.class);
                startActivity(intent);
                finish();
                // выход из уровня
            } else {

                for (Integer integer : imgLeftInUse) {
                    System.out.println(integer);
                }

                btn_right_1.setEnabled(true);
                btn_right_2.setEnabled(true);
                btn_right_3.setEnabled(true);
                btn_left_1.setEnabled(true);
                btn_left_2.setEnabled(true);
                btn_left_3.setEnabled(true);

                btn_left_1.setBackgroundResource(R.drawable.btn_answ_style);
                btn_left_2.setBackgroundResource(R.drawable.btn_answ_style);
                btn_left_3.setBackgroundResource(R.drawable.btn_answ_style);
                btn_right_1.setBackgroundResource(R.drawable.btn_answ_style);
                btn_right_2.setBackgroundResource(R.drawable.btn_answ_style);
                btn_right_3.setBackgroundResource(R.drawable.btn_answ_style);

                numLeft_1 = random.nextInt(array.img_6.length);
                while (numLeft_1 == numLeft_2) {
                    numLeft_2 = random.nextInt(array.img_6.length);
                }
                while (numLeft_2 == numLeft_3 || numLeft_1 == numLeft_3) {
                    numLeft_3 = random.nextInt(array.img_6.length);
                }

                numRight_1 = numLeft_1;
                numRight_2 = numLeft_2;
                numRight_3 = numLeft_3;

                chooseCardLeft = random.nextInt(cardCount);
                if (chooseCardLeft == 0) {
                    btn_left_1.setText(array.img_6[numLeft_1]);
                    btn_left_2.setText(array.img_6[numLeft_2]);
                    btn_left_3.setText(array.img_6[numLeft_3]);
                    flagLeft = 0;
                } else if (chooseCardLeft == 1) {
                    btn_left_1.setText(array.img_6[numLeft_1]);
                    btn_left_2.setText(array.img_6[numLeft_3]);
                    btn_left_3.setText(array.img_6[numLeft_2]);
                    flagLeft = 1;
                } else if (chooseCardLeft == 2) {
                    btn_left_1.setText(array.img_6[numLeft_2]);
                    btn_left_2.setText(array.img_6[numLeft_1]);
                    btn_left_3.setText(array.img_6[numLeft_3]);
                    flagLeft = 2;
                } else if (chooseCardLeft == 3) {
                    btn_left_1.setText(array.img_6[numLeft_2]);
                    btn_left_2.setText(array.img_6[numLeft_3]);
                    btn_left_3.setText(array.img_6[numLeft_1]);
                    flagLeft = 3;
                } else if (chooseCardLeft == 4) {
                    btn_left_1.setText(array.img_6[numLeft_3]);
                    btn_left_2.setText(array.img_6[numLeft_1]);
                    btn_left_3.setText(array.img_6[numLeft_2]);
                    flagLeft = 4;
                } else if (chooseCardLeft == 5) {
                    btn_left_1.setText(array.img_6[numLeft_3]);
                    btn_left_2.setText(array.img_6[numLeft_2]);
                    btn_left_3.setText(array.img_6[numLeft_1]);
                    flagLeft = 5;
                }

                chooseCardRight = random.nextInt(cardCount);
                if (chooseCardRight == 0) {
                    flagRight = 0;
                    btn_right_1.setText(array.text_6[numRight_1]);
                    btn_right_2.setText(array.text_6[numRight_2]);
                    btn_right_3.setText(array.text_6[numRight_3]);
                } else if (chooseCardRight == 1) {
                    flagRight = 1;
                    btn_right_1.setText(array.text_6[numRight_1]);
                    btn_right_2.setText(array.text_6[numRight_3]);
                    btn_right_3.setText(array.text_6[numRight_2]);
                } else if (chooseCardRight == 2) {
                    flagRight = 2;
                    btn_right_1.setText(array.text_6[numRight_2]);
                    btn_right_2.setText(array.text_6[numRight_1]);
                    btn_right_3.setText(array.text_6[numRight_3]);
                } else if (chooseCardRight == 3) {
                    flagRight = 3;
                    btn_right_1.setText(array.text_6[numRight_2]);
                    btn_right_2.setText(array.text_6[numRight_3]);
                    btn_right_3.setText(array.text_6[numRight_1]);
                } else if (chooseCardRight == 4) {
                    flagRight = 4;
                    btn_right_1.setText(array.text_6[numRight_3]);
                    btn_right_2.setText(array.text_6[numRight_1]);
                    btn_right_3.setText(array.text_6[numRight_2]);
                } else if (chooseCardRight == 5) {
                    flagRight = 5;
                    btn_right_1.setText(array.text_6[numRight_3]);
                    btn_right_2.setText(array.text_6[numRight_2]);
                    btn_right_3.setText(array.text_6[numRight_1]);
                }

                final Animation animation = AnimationUtils.loadAnimation(Level_6.this, R.anim.alpha);

                btn_left_1.startAnimation(animation);
                btn_left_2.startAnimation(animation);
                btn_left_3.startAnimation(animation);
                btn_right_1.startAnimation(animation);
                btn_right_2.startAnimation(animation);
                btn_right_3.startAnimation(animation);

                flagChooseRight = 0;
                countTrueAll = 0;
            }
        }
    }

    @Override
    public void onBackPressed() {
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