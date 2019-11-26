package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;



public class pantallaCarga extends AppCompatActivity {
    ImageView progreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                Intent intent = new Intent(pantallaCarga.this,InicioAventuraActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }

}