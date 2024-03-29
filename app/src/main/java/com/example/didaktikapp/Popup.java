package com.example.didaktikapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.didaktikapp.Puzzle_cuadrados.PuzzleActivity;
import com.example.didaktikapp.sopadeletras.features.wordsearch.WordSearchActivity;

public class Popup extends Activity {


    private ImageButton btnErrepikatu, btnJarraitu;
    DBHelper dbHelper;
    SQLiteDatabase db;
    String Nombre;
    int destruir;
    boolean admin = false;
    boolean seleccionado = false;

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

        SharedPreferences sharedpreferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        admin = sharedpreferences.getBoolean("pasado",false);

        //Se las asignamos
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 20;

        getWindow().setAttributes(params);

        //Botón del popup para repetir los juegos
        btnErrepikatu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Recibe el string del juego que se va a repetir
                seleccionado=true;
                if(getIntent().getStringExtra("valor").equals("quiz")){

                    Intent intent = new Intent(Popup.this, Quiz.class);
                    startActivity(intent);
                } else if(getIntent().getStringExtra("valor").equals("quiz4historia")){
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

                else if(getIntent().getStringExtra("valor").equals("deslizar5historia")){
                    Intent intent = new Intent(Popup.this, PuzzleActivity.class);
                    startActivity(intent);
                }
                else if(getIntent().getStringExtra("valor").equals("ordenarImagen")){

                    Intent intent = new Intent(Popup.this, OrdenarImagen.class);
                    startActivity(intent);
                }else if(getIntent().getStringExtra("valor").equals("test")){

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

                else if(getIntent().getStringExtra("valor").equals("testgalderak6historia")){
                    Intent intent = new Intent(Popup.this, TestGalderak.class);
                    startActivity(intent);

                }
                else if(getIntent().getStringExtra("valor").equals("gurutzegrama7historia")){
                    Intent intent = new Intent(Popup.this, GurutzegramaActivity.class);
                    startActivity(intent);

                }else if(getIntent().getStringExtra("valor").equals("sopa2_1historia")){

                    Intent intent = new Intent(Popup.this, WordSearchActivity.class);
                    startActivity(intent);

                }else if(getIntent().getStringExtra("valor").equals("sopa2")){
                    Intent intent = new Intent(Popup.this, WordSearchActivity.class);
                    startActivity(intent);
                }else if(getIntent().getStringExtra("valor").equals("tabla")){

                    Intent intent = new Intent(Popup.this, HutsuneakTabla.class);
                    startActivity(intent);
                }else if (getIntent().getStringExtra("valor").equals("tablaLibre")){
                    Intent intent = new Intent(Popup.this, HutsuneakTabla.class);
                    startActivity(intent);
                } else if(getIntent().getStringExtra("valor").equals("mika7historia")){
                    Intent intent = new Intent(Popup.this, MikaFinal.class);
                    startActivity(intent);
                }else if(getIntent().getStringExtra("valor").equals("mikalibre")){
                    Intent intent = new Intent(Popup.this, MikaFinal.class);
                    startActivity(intent);
                }

            }
        });

        //Botón continuar del popup
        btnJarraitu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                seleccionado = true;
                destruir=1;
                dbHelper = new DBHelper(getApplicationContext());
                db = dbHelper.getWritableDatabase();
                SharedPreferences sharedPref = getSharedPreferences("datos",Context.MODE_PRIVATE);
                Nombre = sharedPref.getString("nombre", null);
                Log.d("tag", "onClick: "+ getIntent().getStringExtra("valor"));

                //Recibe el string del juego
                if(getIntent().getStringExtra("valor").equals("puzzleM1")){
                   /* String strSQL = "UPDATE "+DBHelper.entidadProgreso.TABLE_NAME+" SET "+DBHelper.entidadProgreso.COLUMN_NAME_PTO_1+" = " + 1 +" WHERE "+DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO+" = "+ Nombre;
                    db.execSQL(strSQL);*/

                   /*
                   //Desbloquea los juegos en el menu
                    Juegos J14 = new Juegos("Ondare",getDrawable(R.drawable.quiz));
                    Juegos.getJuegosArrayList().add(J14);

                    Juegos J12 = new Juegos("Taula",getDrawable(R.drawable.tabla));
                    Juegos.getJuegosArrayList().add(J12);
                    Juegos J4 = new Juegos("Puzzle",getDrawable(R.drawable.puzzle));
                    Juegos.getJuegosArrayList().add(J4);

                    */

                    //Si el usuario no ha entrado al menu_admin, desbloquea el siguiente marcador
                    if(!admin){
                        ContentValues values = new ContentValues();
                        values.put(DBHelper.entidadProgreso.COLUMN_NAME_PROGRESO,2);
                        String seleccion = DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO + "= ?";
                        String args [] = {Nombre};
                        int count = db.update(DBHelper.entidadProgreso.TABLE_NAME,values,seleccion,args);
                        //Toast.makeText(getApplicationContext(), " Actualizar lineas "+ count, Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(Popup.this, MapaActivity.class);
                    startActivity(intent);
                }else if (getIntent().getStringExtra("valor").equals("Historia0")){
                    Intent intent = new Intent(Popup.this, MapaActivity.class);
                    String valor  = "historia0";
                    intent.putExtra("valor", valor );
                    startActivity(intent);
                }else if (getIntent().getStringExtra("valor").equals("tablaLibre")) {

                    Intent intent = new Intent(Popup.this, seleccionJuego.class);
                    startActivity(intent);
                } else if(getIntent().getStringExtra("valor").equals("tabla")){
                    Intent intent = new Intent(Popup.this, MikaExplicando.class);
                    intent.putExtra("marcador",1.2);
                    startActivity(intent);
                } else if (getIntent().getStringExtra("valor").equals("puzzleLibre")) {
                    Intent intent = new Intent(Popup.this, seleccionJuego.class);
                    startActivity(intent);
                }else if(getIntent().getStringExtra("valor").equals("sopa2_1historia")){

                    /*
                    Juegos J13 = new Juegos("Hutsuneak bete2",getDrawable(R.drawable.rellenar_hueco));
                    Juegos.getJuegosArrayList().add(J13);
                    Juegos J15 = new Juegos("Hizki salda",getDrawable(R.drawable.sopa));
                    Juegos.getJuegosArrayList().add(J15);

                     */

                    if(!admin) {
                        ContentValues values = new ContentValues();
                        values.put(DBHelper.entidadProgreso.COLUMN_NAME_PROGRESO, 3);
                        String seleccion = DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO + "= ?";
                        String args[] = {Nombre};
                        int count = db.update(DBHelper.entidadProgreso.TABLE_NAME, values, seleccion, args);
                    }
                    Intent intent = new Intent(Popup.this, MapaActivity.class);
                    startActivity(intent);
                }
                else if(getIntent().getStringExtra("valor").equals("ordenar3_1historia")){
               /* String strSQL = "UPDATE "+DBHelper.entidadProgreso.TABLE_NAME+" SET "+DBHelper.entidadProgreso.COLUMN_NAME_PTO_1+" = " + 1 +" WHERE "+DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO+" = "+ Nombre;
                db.execSQL(strSQL);*/

               /*
                    Juegos J8 = new Juegos("Egia/Gezurra",getDrawable(R.drawable.true_false));
                    Juegos.getJuegosArrayList().add(J8);
                    Juegos J10 = new Juegos("Ordenatu Kronologikoki",getDrawable(R.drawable.cronologia));
                    Juegos.getJuegosArrayList().add(J10);

                */
                    if(!admin){
                        ContentValues values = new ContentValues();
                        values.put(DBHelper.entidadProgreso.COLUMN_NAME_PROGRESO,4);
                        String seleccion = DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO + "= ?";
                        String args [] = {Nombre};
                        int count = db.update(DBHelper.entidadProgreso.TABLE_NAME,values,seleccion,args);
                    }
                    //Toast.makeText(getApplicationContext(), " Actualizar lineas "+ count, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Popup.this, MapaActivity.class);
                    startActivity(intent);

                }else if(getIntent().getStringExtra("valor").equals("quiz4historia")){
                   /* String strSQL = "UPDATE "+DBHelper.entidadProgreso.TABLE_NAME+" SET "+DBHelper.entidadProgreso.COLUMN_NAME_PTO_1+" = " + 1 +" WHERE "+DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO+" = "+ Nombre;
                    db.execSQL(strSQL);*/

                   /*
                    Juegos J2 = new Juegos("Quiz",getDrawable(R.drawable.quiz));
                    Juegos.getJuegosArrayList().add(J2);

                    */

                    if(!admin) {
                        ContentValues values = new ContentValues();
                        values.put(DBHelper.entidadProgreso.COLUMN_NAME_PROGRESO, 5);
                        String seleccion = DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO + "= ?";
                        String args[] = {Nombre};
                        int count = db.update(DBHelper.entidadProgreso.TABLE_NAME, values, seleccion, args);
                        //Toast.makeText(getApplicationContext(), " Actualizar lineas "+ count, Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(Popup.this, MapaActivity.class);
                    startActivity(intent);
                }else if(getIntent().getStringExtra("valor").equals("deslizar5historia")){
                   /* String strSQL = "UPDATE "+DBHelper.entidadProgreso.TABLE_NAME+" SET "+DBHelper.entidadProgreso.COLUMN_NAME_PTO_1+" = " + 1 +" WHERE "+DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO+" = "+ Nombre;
                    db.execSQL(strSQL);*/
                   /*
                    Juegos J6 = new Juegos("Puzzle irristagarria",getDrawable(R.drawable.puzzletearrastro));
                    Juegos.getJuegosArrayList().add(J6);
                    Juegos J7 = new Juegos("Hutsuneak bete",getDrawable(R.drawable.rellenar_hueco));
                    Juegos.getJuegosArrayList().add(J7);

                    */

                    if(!admin){
                        ContentValues values = new ContentValues();
                        values.put(DBHelper.entidadProgreso.COLUMN_NAME_PROGRESO,6);
                        String seleccion = DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO + "= ?";
                        String args [] = {Nombre};
                        int count = db.update(DBHelper.entidadProgreso.TABLE_NAME,values,seleccion,args);
                        //Toast.makeText(getApplicationContext(), " Actualizar lineas "+ count, Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(Popup.this, MapaActivity.class);
                    startActivity(intent);
                }else if(getIntent().getStringExtra("valor").equals("testgalderak6historia")){

                    Intent intent = new Intent(Popup.this, MikaExplicando.class);
                    intent.putExtra("marcador",7.1);
                    startActivity(intent);
                }else if(getIntent().getStringExtra("valor").equals("gurutzegrama7historia")){

                    Intent intent = new Intent(Popup.this, MikaFinal.class);
                    String valor  = "mika";
                    intent.putExtra("valor", valor );
                    startActivity(intent);

                }
                else if(getIntent().getStringExtra("valor").equals("mika7historia")){

                    /*
                    Juegos J11 = new Juegos("Test",getDrawable(R.drawable.quiz));
                    Juegos.getJuegosArrayList().add(J11);
                    Juegos J3 = new Juegos("Gurutzegrama",getDrawable(R.drawable.cruzada));
                    Juegos.getJuegosArrayList().add(J3);
                    Juegos J16 = new Juegos("Mika",getDrawable(R.drawable.muztio));
                    Juegos.getJuegosArrayList().add(J16);

                     */

                    if(!admin){
                        ContentValues values = new ContentValues();
                        values.put(DBHelper.entidadProgreso.COLUMN_NAME_PROGRESO,7);
                        String seleccion = DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO + "= ?";
                        String args [] = {Nombre};
                        int count = db.update(DBHelper.entidadProgreso.TABLE_NAME,values,seleccion,args);
                    }
                    Intent intent = new Intent(Popup.this, MapaActivity.class);
                    startActivity(intent);

                }else if(getIntent().getStringExtra("valor").equals("mikalibre")){
                    Intent intent = new Intent(Popup.this, seleccionJuego.class);
                    startActivity(intent);
                } else{

                Intent intent = new Intent(Popup.this,seleccionJuego.class);
                startActivity(intent);
                }
            }
        });
    }


    public void onDestroy() {
        super.onDestroy();
        if(!seleccionado){
            Intent intent = getIntent();
            startActivity(intent);
        }

    }

}
