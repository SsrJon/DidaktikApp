package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.didaktikapp.Puzzle_cuadrados.PuzzleActivity;

public class Popup extends Activity {


    private ImageButton btnErrepikatu, btnJarraitu;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);
        Bundle bundle = getIntent().getExtras();


        btnJarraitu=findViewById(R.id.imageButtonJarraitu);
        btnErrepikatu=findViewById(R.id.imageButtonErrepikatu);

        //Creamos el PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        //Le damos medidas
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width/1.5), (int)(height/4));



        //Se las asignamos
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 20;

        getWindow().setAttributes(params);





        btnErrepikatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getIntent().getStringExtra("valor").equals("quiz")){

                    Intent intent = new Intent(Popup.this, Quiz.class);
                    startActivity(intent);
                }

                else if(getIntent().getStringExtra("valor").equals("gurutzegrama")){

                    Intent intent = new Intent(Popup.this, GurutzegramaActivity.class);
                    startActivity(intent);
                }

                else if(getIntent().getStringExtra("valor").equals("deslizar")){

                    Intent intent = new Intent(Popup.this, PuzzleActivity.class);
                    startActivity(intent);
                }
                else if(getIntent().getStringExtra("valor").equals("ordenarImagen")){

                    Intent intent = new Intent(Popup.this, OrdenarImagen.class);
                    startActivity(intent);
                }
                else if(getIntent().getStringExtra("valor").equals("test")){

                    Intent intent = new Intent(Popup.this, TestGalderak.class);
                    startActivity(intent);
                }



                //Intent intent = new Intent(Popup.this, ActivityHutsuneakBete.class);
                //startActivity(intent);
            }
        });


        btnJarraitu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new DBHelper(getApplicationContext());
                db = dbHelper.getWritableDatabase();
            if(getIntent().getStringExtra("valor").equals("puzzleM1")){
                ContentValues cv = new ContentValues();
                cv.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_1,1); //These Fields should be your String values of actual column names
                db.update(DBHelper.entidadProgreso.TABLE_NAME, cv,null,null);

                Intent intent = new Intent(Popup.this, MapaActivity.class);
                startActivity(intent);
            }


            }
        });



    }
}
