package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.didaktikapp.Puzzle_cuadrados.PuzzleActivity;

public class Popup extends Activity {


    private ImageButton btnErrepikatu, btnJarraitu;
    DBHelper dbHelper;
    SQLiteDatabase db;
    String Nombre;

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
                else if (getIntent().getStringExtra("valor").equals("hutsuneak3historia")) {
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
                else if(getIntent().getStringExtra("valor").equals("test0")){

                    Intent intent = new Intent(Popup.this, Test0.class);
                    startActivity(intent);
                }
                else if (getIntent().getStringExtra("valor").equals("puzzleLibre")){

                    Intent intent = new Intent(Popup.this, com.example.didaktikapp.Puzzle.PuzzleActivity.class);
                    startActivity(intent);
                }
                else if (getIntent().getStringExtra("valor").equals("Historia0")){
                    Intent intent = new Intent(Popup.this, Test0.class);
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
                SharedPreferences sharedPref = getSharedPreferences("datos",Context.MODE_PRIVATE);
                Nombre = sharedPref.getString("nombre", null);

            if(getIntent().getStringExtra("valor").equals("puzzleM1")){
               /* String strSQL = "UPDATE "+DBHelper.entidadProgreso.TABLE_NAME+" SET "+DBHelper.entidadProgreso.COLUMN_NAME_PTO_1+" = " + 1 +" WHERE "+DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO+" = "+ Nombre;
                db.execSQL(strSQL);*/

                ContentValues values = new ContentValues();
                values.put(DBHelper.entidadProgreso.COLUMN_NAME_PROGRESO,2);
                String seleccion = DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO + "= ?";
                String args [] = {Nombre};
                int count = db.update(DBHelper.entidadProgreso.TABLE_NAME,values,seleccion,args);
               //Toast.makeText(getApplicationContext(), " Actualizar lineas "+ count, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Popup.this, MapaActivity.class);
                startActivity(intent);
            }else if (getIntent().getStringExtra("valor").equals("Historia0")){

                Intent intent = new Intent(Popup.this, MapaActivity.class);
                String valor  = "historia0";
                intent.putExtra("valor", valor );
                startActivity(intent);
            }else if (getIntent().getStringExtra("valor").equals("puzzleLibre")) {

                Intent intent = new Intent(Popup.this, seleccionJuego.class);
                startActivity(intent);
            }else if (getIntent().getStringExtra("valor").equals("hutsuneak3historia")) {


                Intent intent = new Intent(Popup.this, OrdenarImagen.class);
                String valor  = "historia3_2";
                intent.putExtra("valor", valor );
                startActivity(intent);
            }else{
                Intent intent = new Intent(Popup.this,seleccionJuego.class);
                startActivity(intent);
            }




            }
        });



    }
}
