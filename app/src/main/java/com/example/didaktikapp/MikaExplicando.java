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
    double Punto=0;
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
        Marcador = intent.getDoubleExtra("marcador",7);
        Punto = intent.getDoubleExtra("punto",0);
        if (Punto == 0){
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
                    intent.putExtra("llegada","marcador");
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
                    intent.putExtra("llegada","marcador1");
                    startActivity(intent);
                }
            });
        }else if(Marcador ==3){

        }else if(Marcador ==4){

        }else if(Marcador ==5){

        }else if(Marcador ==6){

        }
    }
}
