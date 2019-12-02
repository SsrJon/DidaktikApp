package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;



public class PantallaCarga extends AppCompatActivity {
    ImageView progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);


        Permisos();



           Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {


                    // acciones que se ejecutan tras los milisegundos
                    Intent intent = new Intent(PantallaCarga.this,InicioAventuraActivity.class);
                    startActivity(intent);
                }
            }, 5000);





    }


    //Aqui comprobaremos si tenemos los permisos de escritura y si no lo tenemos los pediremos
    private void Permisos(){
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permisocamara = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED || permisocamara != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso de ubicación!.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CAMERA}, 225);

        } else {
            Log.i("Mensaje", "Tienes permiso para usar la ubicación.");


        }
    }



}