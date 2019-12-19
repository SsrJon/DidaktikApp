package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class FotoElegir extends AppCompatActivity {
     ImageView foto1;
     ImageView foto2;
     ImageView foto3;
     ImageView foto4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotoelejir);
        setContentView(R.layout.activity_fotoelejir);
        foto1 = findViewById(R.id.Opcion1);
        foto2 = findViewById(R.id.Opcion2);
        foto3 = findViewById(R.id.Opcion3);
        foto4 = findViewById(R.id.Opcion4);



        foto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hola");



            }
        });


    }






}
