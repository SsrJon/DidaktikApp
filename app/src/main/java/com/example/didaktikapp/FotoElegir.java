package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class FotoElegir extends AppCompatActivity {
    //Juego elegir imagen correcta

     ImageView foto1;
     ImageView foto2;
     ImageView foto3;
     ImageView foto4;
     MediaPlayer mediaPlayerokerra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotoelegir);
        setContentView(R.layout.activity_fotoelegir);
        foto1 = findViewById(R.id.Opcion1);
        foto2 = findViewById(R.id.Opcion2);
        foto3 = findViewById(R.id.Opcion3);
        foto4 = findViewById(R.id.Opcion4);



        //Click listener para cada imageview

        foto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hola");
                mediaPlayerokerra= MediaPlayer.create(getApplicationContext(),R.raw.erantzun_okerra_3_audioa);
                mediaPlayerokerra.start();
                bloquearClick();



            }
        });

        foto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hola");

                Intent popUp = new Intent(FotoElegir.this, PopupHorizontal.class);
                String valor  = "foto_elegir";
                popUp.putExtra("valor", valor );
                startActivity(popUp);



            }
        });

        foto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hola");
                mediaPlayerokerra= MediaPlayer.create(getApplicationContext(),R.raw.erantzun_okerra_3_audioa);
                mediaPlayerokerra.start();
                bloquearClick();



            }
        });

        foto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hola");
                mediaPlayerokerra= MediaPlayer.create(getApplicationContext(),R.raw.erantzun_okerra_3_audioa);
                mediaPlayerokerra.start();
                bloquearClick();



            }
        });


    }


    //Bloquea clickable durante 3 segundos, tiempo suficiente para que suene el audio
    public void bloquearClick(){

        foto1.setClickable(false);
        foto2.setClickable(false);
        foto3.setClickable(false);
        foto4.setClickable(false);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                foto1.setClickable(true);
                foto2.setClickable(true);
                foto3.setClickable(true);
                foto4.setClickable(true);

            }
        }, 3000);


    }






}
