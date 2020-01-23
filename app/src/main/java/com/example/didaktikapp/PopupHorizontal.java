package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.didaktikapp.Puzzle_cuadrados.PuzzleActivity;
import com.example.didaktikapp.sopadeletras.features.wordsearch.WordSearchActivity;


public class PopupHorizontal extends Activity {

    private ImageButton btnErrepikatu, btnJarraitu;
    DBHelper dbHelper;
    SQLiteDatabase db;
    String Nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_horizontal);



        Bundle bundle = getIntent().getExtras();


        btnJarraitu=findViewById(R.id.imageButtonJarraituH);
        btnErrepikatu=findViewById(R.id.imageButtonErrepikatuH);

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

                if(getIntent().getStringExtra("valor").equals("hutsuneak")){

                    Intent intent = new Intent(PopupHorizontal.this, ActivityHutsuneakBete.class);
                    startActivity(intent);
                }
                if (getIntent().getStringExtra("valor").equals("hutsuneak5historia")) {
                    Intent intent = new Intent(PopupHorizontal.this, ActivityHutsuneakBete.class);
                    startActivity(intent);
                }

                if(getIntent().getStringExtra("valor").equals("hutsuneak2")){

                    Intent intent = new Intent(PopupHorizontal.this, ActivityHutsuneakBete2.class);
                    startActivity(intent);
                }if(getIntent().getStringExtra("valor").equals("egia3historia")){

                    Intent intent = new Intent(PopupHorizontal.this, EgiaedoGezurra.class);
                    startActivity(intent);
                }
                 if (getIntent().getStringExtra("valor").equals("hutsuneak2historia")){
                     Intent intent = new Intent(PopupHorizontal.this, ActivityHutsuneakBete2.class);
                     startActivity(intent);
                 }

                if(getIntent().getStringExtra("valor").equals("egia")){

                    Intent intent = new Intent(PopupHorizontal.this, EgiaedoGezurra.class);
                    startActivity(intent);
                }

                if(getIntent().getStringExtra("valor").equals("foto_elegir")){

                    Intent intent = new Intent(PopupHorizontal.this, FotoElegir.class);
                    startActivity(intent);
                }

                  if (getIntent().getStringExtra("valor").equals("Historia")){

                  }


            }
        });


        btnJarraitu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper = new DBHelper(getApplicationContext());
                db = dbHelper.getWritableDatabase();
                SharedPreferences sharedPref = getSharedPreferences("datos", Context.MODE_PRIVATE);
                Nombre = sharedPref.getString("nombre", null);


                 if (getIntent().getStringExtra("valor").equals("Historia")){

                    Intent intent = new Intent(PopupHorizontal.this, MapaActivity.class);
                    startActivity(intent);
                }else if (getIntent().getStringExtra("valor").equals("hutsuneak2historia")) {



                    Intent intent = new Intent(PopupHorizontal.this, WordSearchActivity.class);
                     String valor  = "sopa2historia";
                     intent.putExtra("valor", valor );
                    startActivity(intent);
                }else if (getIntent().getStringExtra("valor").equals("egia3historia")) {

                    Intent intent = new Intent(PopupHorizontal.this, OrdenarImagen.class);
                    String valor  = "egia3_1historia";
                    intent.putExtra("valor", valor );
                    startActivity(intent);
                }else if (getIntent().getStringExtra("valor").equals("hutsuneak5historia")) {

                    Intent intent = new Intent(PopupHorizontal.this, PuzzleActivity.class);
                    String valor  = "hutsuneak5_1historia";
                    intent.putExtra("valor", valor );
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(PopupHorizontal.this, seleccionJuego.class);
                    startActivity(intent);
                }




            }
        });
    }



    public void onDestroy() {
        dbHelper = new DBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        SharedPreferences sharedPref = getSharedPreferences("datos", Context.MODE_PRIVATE);
        Nombre = sharedPref.getString("nombre", null);


        if(getIntent().getStringExtra("valor").equals("tabla")){
            Intent intent = new Intent(PopupHorizontal.this, MikaExplicando.class);
            intent.putExtra("marcador",1.2);
            startActivity(intent);
        } else if (getIntent().getStringExtra("valor").equals("Historia")){

            Intent intent = new Intent(PopupHorizontal.this, MapaActivity.class);
            startActivity(intent);
        }else if (getIntent().getStringExtra("valor").equals("tablaLibre")) {

            Intent intent = new Intent(PopupHorizontal.this, seleccionJuego.class);
            startActivity(intent);
        }else if (getIntent().getStringExtra("valor").equals("hutsuneak2historia")) {

            Intent intent = new Intent(PopupHorizontal.this, WordSearchActivity.class);
            String valor  = "sopa2historia";
            intent.putExtra("valor", valor );
            startActivity(intent);


/*


 */
        }else if (getIntent().getStringExtra("valor").equals("egia3historia")) {

            Intent intent = new Intent(PopupHorizontal.this, OrdenarImagen.class);
            String valor  = "egia3_1historia";
            intent.putExtra("valor", valor );
            startActivity(intent);
        }else if (getIntent().getStringExtra("valor").equals("hutsuneak5historia")) {

            Intent intent = new Intent(PopupHorizontal.this, PuzzleActivity.class);
            String valor  = "hutsuneak5_1historia";
            intent.putExtra("valor", valor );
            startActivity(intent);
        }else{
            Intent intent = new Intent(PopupHorizontal.this, seleccionJuego.class);
            startActivity(intent);
        }


        super.onDestroy();
    }
}
