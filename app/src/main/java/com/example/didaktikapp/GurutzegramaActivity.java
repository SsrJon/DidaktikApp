package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GurutzegramaActivity extends AppCompatActivity {

    private Button uno;
    private Button  dos;
    private Button  tres;
    private Button  cuatro;
    private Button  cinco;
    private Button  seis;
    private Button  responder;
    private String respuesta;
    private ConstraintLayout constraintLayout;
    private TextView pregunta;
    private String mostrar;
    private EditText respuestaEscrita;
    private ImageView imagenUno;
    private ImageView imagenDos;
    private ImageView imagenTres;
    private ImageView imagenCuatro;
    private ImageView imagenCinco;
    private ImageView imagenSeis;
    private  int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gurutzegrama);
        uno = findViewById(R.id.gurutzegramaUno);
        dos = findViewById(R.id.gurutzegramaDos);
        tres = findViewById(R.id.gurutzegramaTres);
        cuatro = findViewById(R.id.gurutzegramaCuatro);
        cinco = findViewById(R.id.gurutzegramaCinco);
        seis = findViewById(R.id.gurutzegramaSeis);
        constraintLayout = findViewById(R.id.responderGurutzegrama);
        responder = findViewById(R.id.responderPregunta);
        pregunta = findViewById(R.id.textoPregunta);
        respuestaEscrita = findViewById(R.id.escribirRespuesta);
        imagenUno = findViewById(R.id.respuestaUno);
        imagenDos = findViewById(R.id.respuestaDos);
        imagenTres = findViewById(R.id.respuestaTres);
        imagenCuatro = findViewById(R.id.respuestaCuatro);
        imagenCinco = findViewById(R.id.respuestaCinco);
        imagenSeis = findViewById(R.id.respuestaSeis);
        contador = 0;

        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = getString(R.string.respuestaUno);
                constraintLayout.setVisibility(View.VISIBLE);
                pregunta.setText(getString(R.string.verticalUno));
                respuestaEscrita.setText("");
                mostrar = "1";

            }
        });

        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = getString(R.string.respuestaDos);
                constraintLayout.setVisibility(View.VISIBLE);
                pregunta.setText(getString(R.string.horizontalUno));
                respuestaEscrita.setText("");
                mostrar = "2";
            }
        });

        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = getString(R.string.respuestaTres);
                constraintLayout.setVisibility(View.VISIBLE);
                pregunta.setText(getString(R.string.verticalDos));
                respuestaEscrita.setText("");
                mostrar = "3";
            }
        });

        cuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = getString(R.string.respuestaCuatro);
                constraintLayout.setVisibility(View.VISIBLE);
                pregunta.setText(getString(R.string.horizontalDos));
                respuestaEscrita.setText("");
                mostrar = "4";
            }
        });

        cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = getString(R.string.respuestaCinco);
                constraintLayout.setVisibility(View.VISIBLE);
                pregunta.setText(getString(R.string.horizontalTres));
                respuestaEscrita.setText("");
                mostrar = "5";
            }
        });

        seis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = getString(R.string.respuestaSeis);
                constraintLayout.setVisibility(View.VISIBLE);
                pregunta.setText(getString(R.string.horizontalCuatro));
                respuestaEscrita.setText("");
                mostrar = "6";
            }
        });
        responder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "hola  "+respuestaEscrita.getText().toString());
                Log.d("tag", "adios "+respuesta);
                if(respuestaEscrita.getText().toString().equals(respuesta)){
                    contador++;
                    if(contador == 6){
                        MediaPlayer mediaPlayer= MediaPlayer.create(GurutzegramaActivity.this,R.raw.erantzunzuzena6_audioa);
                        mediaPlayer.start();
                    }

                    switch (mostrar){
                        case "1":
                            uno.setVisibility(View.INVISIBLE);
                            imagenUno.setVisibility(View.VISIBLE);
                            break;
                        case "2":
                            dos.setVisibility(View.INVISIBLE);
                            imagenDos.setVisibility(View.VISIBLE);
                            break;
                        case "3":
                            tres.setVisibility(View.INVISIBLE);
                            imagenTres.setVisibility(View.VISIBLE);
                            break;
                        case "4":
                            cuatro.setVisibility(View.INVISIBLE);
                            imagenCuatro.setVisibility(View.VISIBLE);
                            break;
                        case "5":
                            cinco.setVisibility(View.INVISIBLE);
                            imagenCinco.setVisibility(View.VISIBLE);
                            break;
                        case "6":
                            seis.setVisibility(View.INVISIBLE);
                            imagenSeis.setVisibility(View.VISIBLE);
                            break;
                    }
                    constraintLayout.setVisibility(View.INVISIBLE);
                }else{
                    MediaPlayer mediaPlayer= MediaPlayer.create(GurutzegramaActivity.this,R.raw.erantzunokerra6_audioa);
                    mediaPlayer.start();
                    constraintLayout.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
