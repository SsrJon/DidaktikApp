package com.example.didaktikapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Menu_admin extends AppCompatActivity {
    Button borrarUsuario;
    Button borrarProgreso;
    Button desbloquearProgreso;
    Button cambiarnombre;
    DBHelper dbHelper;
    SQLiteDatabase db;
    String Nombre;
    EditText cambiarNombre;
    EditText escribirAdmin;
    Button respoderAdmin;
    ConstraintLayout responderAdminLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        dbHelper = new DBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        SharedPreferences sharedPref = getSharedPreferences("datos", Context.MODE_PRIVATE);
        Nombre = sharedPref.getString("nombre", null);
        cambiarNombre = findViewById(R.id.aldatuizenalabel);
        cambiarNombre.setHint(Nombre);
        escribirAdmin = findViewById(R.id.escribirAdmin);
        responderAdminLayout = findViewById(R.id.responderAdminlayout);

        //Cambia el nombre del alumno
        cambiarnombre = findViewById(R.id.cambiarnombre);
        cambiarnombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombrenuevo;
                nombrenuevo = cambiarNombre.getText().toString();
                //Actualizar nombre
                ContentValues values = new ContentValues();
                values.put(DBHelper.entidadUsuario.COLUMN_NAME_NOMBRE,nombrenuevo);
                String seleccion = DBHelper.entidadUsuario.COLUMN_NAME_NOMBRE + "= ?";
                String args [] = {Nombre};
                int count = db.update(DBHelper.entidadUsuario.TABLE_NAME,values,seleccion,args);
                //actualizar nombre progreso
                ContentValues valuespro = new ContentValues();
                valuespro.put(DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO,nombrenuevo);
                String seleccionpro = DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO + "= ?";
                String argspro [] = {Nombre};
                int countpro = db.update(DBHelper.entidadProgreso.TABLE_NAME,valuespro,seleccionpro,argspro);
                SharedPreferences preferencias = getApplicationContext().getSharedPreferences("datos",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("nombre", nombrenuevo);
                editor.commit();
                Intent intent = new Intent(Menu_admin.this,PantallaCarga.class);
                startActivity(intent);
            }
        });

        //Borra el progreso actual del usuario
        borrarProgreso = findViewById(R.id.borrarProgre);
        borrarProgreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(DBHelper.entidadProgreso.COLUMN_NAME_PROGRESO,1);
                String seleccion = DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO + "= ?";
                String args [] = {Nombre};
                int count = db.update(DBHelper.entidadProgreso.TABLE_NAME,values,seleccion,args);
                Intent intent = new Intent(Menu_admin.this,MapaActivity.class);
                startActivity(intent);
            }
        });

        //Borra el usuario actual
        borrarUsuario = findViewById(R.id.erabiltzaileaEzabatu);
        borrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    db.execSQL("DELETE FROM "+ DBHelper.entidadUsuario.TABLE_NAME + " WHERE " + DBHelper.entidadUsuario. COLUMN_NAME_NOMBRE + " LIKE " + "'+"+Nombre+"+'" );
                    Intent intent = new Intent(Menu_admin.this,PantallaCarga.class);
                    startActivity(intent);
                }catch (Exception e){
                    System.out.println(e);
                    Intent intent = new Intent(Menu_admin.this,PantallaCarga.class);
                    startActivity(intent);
                }

            }
        });
        //Desbloquea todos los marcadores y juegos con el usuario actual
        desbloquearProgreso = findViewById(R.id.desbloquearProgre);
        desbloquearProgreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursorcantidad = db.query(DBHelper.entidadLugares.TABLE_NAME,null,null,null,null,null,null);
                int numLugares =cursorcantidad.getCount();
                rellenarJuegos();
                ContentValues values = new ContentValues();
                values.put(DBHelper.entidadProgreso.COLUMN_NAME_PROGRESO,numLugares+1);
                String seleccion = DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO + "= ?";
                String args [] = {Nombre};
                int count = db.update(DBHelper.entidadProgreso.TABLE_NAME,values,seleccion,args);
                Intent intent = new Intent(Menu_admin.this,MapaActivity.class);
                startActivity(intent);
            }
        });

        //Contraseña que se piede al entrar a la pantalla
       respoderAdmin =findViewById(R.id.respoderAdmin);
       respoderAdmin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(escribirAdmin.getText().toString().equals("muztio")){
                   responderAdminLayout.setVisibility(View.INVISIBLE);
               }else{
                   Intent intent = new Intent(Menu_admin.this,MapaActivity.class);
                   startActivity(intent);
               }
           }
       });

    }

    //Añade todos los juegos al menu de juegos (recycler)
    public void rellenarJuegos(){

        Juegos.getJuegosArrayList().clear();

        Juegos J14 = new Juegos("Ondare kulturala",getDrawable(R.drawable.test));
        Juegos.getJuegosArrayList().add(J14);
        Juegos J12 = new Juegos("Larrea eskultura taula",getDrawable(R.drawable.tabla2));
        Juegos.getJuegosArrayList().add(J12);
        Juegos J4 = new Juegos("Larrea eskultura puzzle",getDrawable(R.drawable.puzzle));
        Juegos.getJuegosArrayList().add(J4);
        Juegos J6 = new Juegos("Landaederreaga puzzle",getDrawable(R.drawable.puzzletearrastro));
        Juegos.getJuegosArrayList().add(J6);
        Juegos J7 = new Juegos("Landaederreaga hutsuneak",getDrawable(R.drawable.rellenar_hueco));
        Juegos.getJuegosArrayList().add(J7);
        Juegos J13 = new Juegos("Udaletxea hutsuneak",getDrawable(R.drawable.dragicon));
        Juegos.getJuegosArrayList().add(J13);
        Juegos J15 = new Juegos("Udaletxea hizki salda",getDrawable(R.drawable.sopa));
        Juegos.getJuegosArrayList().add(J15);
        Juegos J8 = new Juegos("Magdalena Egia/Gezurra",getDrawable(R.drawable.true_false));
        Juegos.getJuegosArrayList().add(J8);
        Juegos J10 = new Juegos("Magdalena Ordenatu Kronologikoki",getDrawable(R.drawable.cronologia));
        Juegos.getJuegosArrayList().add(J10);
        Juegos J2 = new Juegos("Hiltegia quiz",getDrawable(R.drawable.test2));
        Juegos.getJuegosArrayList().add(J2);
        Juegos J11 = new Juegos("San Pedro quiz",getDrawable(R.drawable.quiz));
        Juegos.getJuegosArrayList().add(J11);
        Juegos J3 = new Juegos("Gurutzegrama",getDrawable(R.drawable.cruzada));
        Juegos.getJuegosArrayList().add(J3);
        Juegos J16 = new Juegos("Mika",getDrawable(R.drawable.muztio));
        Juegos.getJuegosArrayList().add(J16);


    }



}
