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

import java.util.ArrayList;

public class Menu_admin extends AppCompatActivity {
    Button borrarUsuario;
    Button borrarProgreso;
    Button desbloquearProgreso;
    DBHelper dbHelper;
    SQLiteDatabase db;
    String Nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        dbHelper = new DBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        SharedPreferences sharedPref = getSharedPreferences("datos", Context.MODE_PRIVATE);
        Nombre = sharedPref.getString("nombre", null);
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
