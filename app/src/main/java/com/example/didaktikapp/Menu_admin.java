package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Menu_admin extends AppCompatActivity {
    Button borrarUsuario;
    Button borrarProgreso;
    Button desbloquearProgreso;
    Button cambiarnombre;
    DBHelper dbHelper;
    SQLiteDatabase db;
    String Nombre;
    EditText cambiarNombre;

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
            }
        });
        borrarProgreso = findViewById(R.id.borrarProgre);
        borrarProgreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(DBHelper.entidadProgreso.COLUMN_NAME_PROGRESO,1);
                String seleccion = DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO + "= ?";
                String args [] = {Nombre};
                int count = db.update(DBHelper.entidadProgreso.TABLE_NAME,values,seleccion,args);
            }
        });
        borrarUsuario = findViewById(R.id.erabiltzaileaEzabatu);
        borrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("DELETE FROM "+ DBHelper.entidadUsuario.TABLE_NAME + " WHERE " + DBHelper.entidadUsuario.COLUMN_NAME_NOMBRE + " LIKE " + Nombre);
                Intent intent = new Intent(Menu_admin.this,PantallaCarga.class);
                startActivity(intent);
            }
        });
        desbloquearProgreso = findViewById(R.id.desbloquearProgre);
        desbloquearProgreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(DBHelper.entidadProgreso.COLUMN_NAME_PROGRESO,7);
                String seleccion = DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO + "= ?";
                String args [] = {Nombre};
                int count = db.update(DBHelper.entidadProgreso.TABLE_NAME,values,seleccion,args);
            }
        });
    }

}
