package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.didaktikapp.Puzzle.PuzzleActivity;

//import com.example.didaktikapp.Puzzle_cuadrados.PuzzleActivity;

public class MikaExplicando extends AppCompatActivity {
    TextView texto;
    double Marcador = 7;
    //double Punto=1;
    private MediaPlayer mediaPlayer;
    Button continuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mika_explicando);
        texto = findViewById(R.id.explicacion);
        Intent intent = getIntent();
        Intent pasar = new Intent();
        continuar = findViewById(R.id.continuar);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });
        Marcador = intent.getDoubleExtra("marcador",0);
        //Punto = intent.getDoubleExtra("punto",0);
        if (Marcador == 0){
            texto.setText(R.string.audio_p0);
            mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.gunea0_audioa);
            mediaPlayer.start();
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    Intent intent = new Intent(MikaExplicando.this, Test0.class);
                    String valor  = "Historia";
                    intent.putExtra("valor", valor );
                    startActivity(intent);
                }
            });
        }
        else if (Marcador == 1.1){
            texto.setText(R.string.explicacionpunto1);
            mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.larrea_eskultura0_audioa);
            mediaPlayer.start();
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    Intent intent = new Intent(MikaExplicando.this,HutsuneakTabla.class);
                    String valor  = "historia0";
                    intent.putExtra("valor", valor );
                    startActivity(intent);
                }
            });
        }else if(Marcador ==1.2){
            texto.setText(R.string.explicacionpunto12);
            mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.larrea_eskultura_1_audioa);
            mediaPlayer.start();
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    Intent intent = new Intent(MikaExplicando.this, PuzzleActivity.class);
                    String valor  = "historia0";
                    intent.putExtra("valor", valor );
                    startActivity(intent);
                }
            });
        }else if(Marcador ==2.1){

            texto.setText(R.string.audio_p2);
            mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.arrigorriagako_udaletxea_audioa);
            mediaPlayer.start();
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    Intent intent = new Intent(MikaExplicando.this, ActivityHutsuneakBete2.class);
                    String valor  = "historia2";
                    intent.putExtra("valor", valor );
                    startActivity(intent);
                }
            });





        }else if(Marcador ==3.1){

            texto.setText(R.string.audio_p3);
            mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.andra_maria_magdalena_eliza_audioa);
            mediaPlayer.start();
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    Intent intent = new Intent(MikaExplicando.this, EgiaedoGezurra.class);
                    String valor  = "historia3";
                    intent.putExtra("valor", valor );
                    startActivity(intent);
                }
            });


        }else if(Marcador ==4.1){

            texto.setText(R.string.audio_p4);
            mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.lehenengo_hiltegia_audioa);
            mediaPlayer.start();
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    Intent intent = new Intent(MikaExplicando.this, Quiz.class);
                    String valor  = "historia4";
                    intent.putExtra("valor", valor );
                    startActivity(intent);
                }
            });


        }else if(Marcador ==5.1){
            texto.setText(R.string.audio_p5);
            mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.santo_cristo_de_landarreaga_ermita_audioa);
            mediaPlayer.start();
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    Intent intent = new Intent(MikaExplicando.this, ActivityHutsuneakBete.class);
                    String valor  = "historia5";
                    intent.putExtra("valor", valor );
                    startActivity(intent);
                }
            });


        }else if(Marcador ==6.1){

            texto.setText(R.string.audio_p6);
            mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.abrisketako_san_pedro_ermita_audioa);
            mediaPlayer.start();
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    Intent intent = new Intent(MikaExplicando.this, TestGalderak.class);
                    String valor  = "historia6";
                    intent.putExtra("valor", valor );
                    startActivity(intent);
                }
            });
        }
    }
}
