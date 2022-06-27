package space.korea.koreanworld;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level23 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft; //переменная для левой картинки + текст
    public int numRight; //переменная для правой картинки + текст
    Array array = new Array();// создали новый объект из класса Array
    Random random = new Random(); //Для генерации случайных числел
    public int count = 0; // Счетчик правильных ответов


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //Создаем переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level4);// установили текст
        text_levels.setTextColor(R.color.black95);

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        //код который сркругляет углы левой картинки
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        //код который сркругляет углы правой картинки
        img_right.setClipToOutline(true);

        //Путь к левой TextView
        final TextView text_left = findViewById(R.id.text_left);
        text_left.setTextColor(R.color.black95);
        //Путь к правой TextView
        final TextView text_right = findViewById(R.id.text_right);
        text_right.setTextColor(R.color.black95);


        // Развернуть игру на весть экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        // Развернуть игру на весть экран - конец

        //Устанавливаем фон  - начало
        ImageView background = (ImageView)findViewById(R.id.background);
        background.setImageResource(R.drawable.level4);
        //Устанавливаем фон - конец

        //Вызов диалогового окна в начале игры
        dialog = new Dialog(this); //создаем новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок
        dialog.setContentView(R.layout.previewdialog); // путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialog.setCancelable(false); // окно нельзя закрыть кнопкой "назад"

        //Устанавливаем картинку в диалоговое окно - начало
        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimg4);
        //Устанавливаем картинку в диалоговое окно - конец

        //Устанавливаем фон диалогового окна - начало
        LinearLayout dialogfon = (LinearLayout)dialog.findViewById(R.id.dialogfon);
        dialogfon.setBackgroundResource(R.drawable.previewbackground4);
        //Устанавливаем фон диалогового окна - конец

        //Устанавливаем описание задания - начало
        TextView textdescription = (TextView)dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.levelfour);
        //Устанавливаем описание задания - конец



        //Кнопка, которая закрывает диалоговое окно - начало
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
          btnclose.setOnClickListener(view -> {
              //обрабатывем нажатие кнопки - начало
              try {
                  //Вернуться назад к выбору уровня - начало
                  Intent intent = new Intent(Level23.this, GameLevels.class);
                  startActivity(intent);finish();
                  //Вернуться назад к выбору уровня - конец
              } catch (Exception e){
                  //кода нет
              }
              dialog.dismiss(); // закрываем диалоговое окно
              //обрабатывем нажатие кнопки - конец
          });
        //Кнопка, которая закрывает диалоговое окно - конец

        //Кнопка "продолжить" - начало
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(view -> dialog.dismiss());
        //Кнопка "продолжить" - конец

        dialog.show(); // показывает диалоговое окно

        //________________________________________
        //Вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this); //создаем новое диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок
        dialogEnd.setContentView(R.layout.dialogend); // путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false); // окно нельзя закрыть кнопкой "назад"

        //устанавливавем фон диалогового окна - начало
        LinearLayout dialogfonEnd = (LinearLayout)dialogEnd.findViewById(R.id.dialogfon);
        dialogfonEnd.setBackgroundResource(R.drawable.previewbackground4);
        //устанавливавем фон диалогового окна - начало

        //Интересный факт-начало
        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.textdescriptionEnd);
        textdescriptionEnd.setText(R.string.levelfourEnd);
        //Интересный факт-конец

        //Кнопка, которая закрывает диалоговое окно - начало
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(view -> {
            //обрабатывем нажатие кнопки - начало
            try {
                //Вернуться назад к выбору уровня - начало
                Intent intent = new Intent(Level23.this, GameLevels.class);
                startActivity(intent);finish();
                //Вернуться назад к выбору уровня - конец
            } catch (Exception e){
                //кода нет
            }
            dialogEnd.dismiss(); // закрываем диалоговое окно
            //обрабатывем нажатие кнопки - конец
        });
        //Кнопка, которая закрывает диалоговое окно - конец

        //Кнопка "продолжить" - начало
        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener((v) -> {
            try {
                Intent intent = new Intent(Level23.this, GameLevels.class);
                startActivity(intent); finish();
            } catch (Exception e) {
                //кода нет
            }

            dialogEnd.dismiss(); // закрываем диалоговое окно
            //Кнопка "продолжить" - конец
        });
        //кнопка продолжить конец

        //________________________________________

        //Копка "Назад" - начало
        Button btn_back = (Button)findViewById(R.id.button_back_levels);
        btn_back.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
        btn_back.setTextColor(R.color.black95);
        btn_back.setOnClickListener(view -> {
            try{
                //Вернуться назад к выбору уровня - начало
                Intent intent = new Intent(Level23.this, GameLevels.class);
                startActivity(intent); finish();
                //Вернуться назад к выбору уровня - конец
            } catch (Exception e) {
                //кода нет
            }
        });
        //Копка "Назад" - конец

        //Массив для прогресса игры - начало
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4,
                R.id.point5, R.id.point6, R.id.point7, R.id.point8, R.id.point9,
                R.id.point10, R.id.point11,R.id.point12,R.id.point13, R.id.point14,
                R.id.point15, R.id.point16,R.id.point17, R.id.point18, R.id.point19, R.id.point20
        };
        //Массив для прогресса игры - конец

        //Подключение анимации - начало
        final Animation a = AnimationUtils.loadAnimation(Level23.this, R.anim.alpha);
        //Подключение анимации - конец

        numLeft = random.nextInt(20); //генерируем случайное число от 0 до 20
        img_left.setImageResource(array.images4[numLeft]);//Достаем из массива картинку
        text_left.setText(array.texts4[numLeft]);//Достаем из массива текст

        numRight = random.nextInt(20); //генерируем случайное число от 0 до 20

        //Цикл в спредисловием, проверяющий равенство чисел - начало
        while (array.strong[numLeft]==array.strong[numRight]){
            numRight = random.nextInt(20);
        }
        //Цикл в спредисловием, проверяющий равенство чисел - конец

        img_right.setImageResource(array.images4[numRight]);//Достаем из массива картинку
        text_right.setText(array.texts4[numRight]);//Достаем из массива текст

        //Обрабатываем нажатие на левую картинку - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent Event) {
                //Условие касания картинки - начало
                if(Event.getAction()==MotionEvent.ACTION_DOWN){
                    //Если коснулся картинки - начало
                    img_right.setEnabled(false);// блокируем правую картинку
                    if(array.strong[numLeft]>array.strong[numRight]){
                        img_left.setImageResource(R.drawable.img_true);
                    } else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    ////Если коснулся картинки - конец

                } else if(Event.getAction()==MotionEvent.ACTION_UP){
                    //Если опустил палец - начало
                    if(array.strong[numLeft]>array.strong[numRight]){
                        if(count<20){
                            count = count + 1;
                        }
                        //Закрашиваем прогресс серым цветом - начало
                           for(int i=0; i<20; i++) {
                               TextView tv = findViewById(progress[i]);
                               tv.setBackgroundResource(R.drawable.style_points);
                           }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                          for (int i=0; i<count; i++) {
                              TextView tv = findViewById(progress[i]);
                              tv.setBackgroundResource(R.drawable.style_points_green);
                          }
                        //Определяем правильные ответы и закрашиваем зеленым - конец

                    } else {
                        //Если левая картинка меньше
                        if(count>0){
                            if(count==1){
                                count=0;
                            } else {
                                count=count-2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for(int i=0; i<19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - конец

                    }
                    //Если опустил палец - конец
                      if(count==20){
                            //ВЫХОД ИЗ УРОВНЯ

                          SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                          final int level = save.getInt("Level",1);
                          if(level>23){
                              //пусто
                          } else {
                              SharedPreferences.Editor editor = save.edit();
                              editor.putInt("Level",24);
                              editor.commit();
                          };

                          dialogEnd.show();
                      } else {
                          numLeft = random.nextInt(20); //генерируем случайное число от 0 до 20
                          img_left.setImageResource(array.images4[numLeft]);//Достаем из массива картинку
                          text_left.setText(array.texts4[numLeft]);//Достаем из массива текст

                          numRight = random.nextInt(20); //генерируем случайное число от 0 до 20

                          //Цикл в спредисловием, проверяющий равенство чисел - начало
                          while (array.strong[numLeft]==array.strong[numRight]){
                              numRight = random.nextInt(20);
                          }
                          //Цикл в спредисловием, проверяющий равенство чисел - конец

                          img_right.setImageResource(array.images4[numRight]);//Достаем из массива картинку
                          text_right.setText(array.texts4[numRight]);//Достаем из массива текст

                          img_right.setEnabled(true); //Включаем обратно правую картинку
                      }

                }
                //Условие касания картинки - конец

                return true;
            }
        });
        //Обрабатываем нажатие на левую картинку - конец



        //Обрабатываем нажатие на правую картинку - начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent Event) {
                //Условие касания картинки - начало
                if(Event.getAction()==MotionEvent.ACTION_DOWN){
                    //Если коснулся картинки - начало
                    img_left.setEnabled(false);// блокируем левую картинку
                    if(array.strong[numLeft]<array.strong[numRight]){
                        img_right.setImageResource(R.drawable.img_true);
                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    ////Если коснулся картинки - конец

                } else if(Event.getAction()==MotionEvent.ACTION_UP){
                    //Если опустил палец - начало
                    if(array.strong[numLeft]<array.strong[numRight]){
                        if(count<20){
                            count = count + 1;
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for(int i=0; i<20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - конец

                    } else {
                        //Если правая картинка меньше
                        if(count>0){
                            if(count==1){
                                count=0;
                            } else {
                                count=count-2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for(int i=0; i<19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - конец

                    }
                    //Если опустил палец - конец
                    if(count==20){
                        //ВЫХОД ИЗ УРОВНЯ

                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if(level>23){
                            //пусто
                        } else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",24);
                            editor.commit();
                        };

                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(20); //генерируем случайное число от 0 до 20
                        img_left.setImageResource(array.images4[numLeft]);//Достаем из массива картинку
                        text_left.setText(array.texts4[numLeft]);//Достаем из массива текст

                        numRight = random.nextInt(20); //генерируем случайное число от 0 до 20

                        //Цикл в спредисловием, проверяющий равенство чисел - начало
                        while (array.strong[numLeft]==array.strong[numRight]){
                            numRight = random.nextInt(20);
                        }
                        //Цикл в спредисловием, проверяющий равенство чисел - конец

                        img_right.setImageResource(array.images4[numRight]);//Достаем из массива картинку
                        text_right.setText(array.texts4[numRight]);//Достаем из массива текст

                        img_left.setEnabled(true); //Включаем обратно левую картинку
                    }

                }
                //Условие касания картинки - конец

                return true;
            }
        });
        //Обрабатываем нажатие на правую картинку - конец

    }
    //Системная кнопка "Назад" - начало
    @Override
    public void onBackPressed(){
        try{
            //Вернуться назад к выбору уровня - начало
            Intent intent = new Intent(Level23.this, GameLevels.class);
            startActivity(intent); finish();
            //Вернуться назад к выбору уровня - конец
        } catch (Exception e) {
            //кода нет
        }
    }
    //Системная кнопка "Назад" - конец
}