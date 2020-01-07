package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class OrdenarImagen extends AppCompatActivity {

    private TextView orden;
    private Button zuzendu;
    private ImageView iglesiaA;
    private ImageView iglesiaB;
    private ImageView iglesiaC;
    private ImageView iglesiaD;
    private String respuesta = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenar_imagen);
        orden = findViewById(R.id.ordenRespuesta);
        zuzendu = findViewById(R.id.responderOrden);
        iglesiaA = findViewById(R.id.iglesia_a);
        iglesiaB = findViewById(R.id.iglesia_b);
        iglesiaC = findViewById(R.id.iglesia_c);
        iglesiaD = findViewById(R.id.iglesia_d);
       //comprobamos que lo escrito coincida con la respuesta correcta
       /* orden.setImeActionLabel("Custom text",KeyEvent.KEYCODE_ENTER );
        orden.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                   /* if (orden.getText().toString().equals( getString(R.string.ordenBueno))||
                            orden.getText().toString().equals(getString(R.string.ordenBuenoDos)) ||
                            orden.getText().toString().equals(getString(R.string.ordenBuenoTres))){
                        MediaPlayer mediaPlayer= MediaPlayer.create(OrdenarImagen.this,R.raw.erantzun_zuzena_3_audioa);
                        mediaPlayer.start();
                        orden.setKeyListener(null);
                        //popUp para volver a jugar al juego o ir al menu de juegos
                        Intent popUp = new Intent(OrdenarImagen.this, Popup.class);
                        String valor  = "ordenarImagen";
                        popUp.putExtra("valor", valor );
                        startActivity(popUp);
                    }
                    else{
                        MediaPlayer mediaPlayer= MediaPlayer.create(OrdenarImagen.this,R.raw.erantzun_okerra_3_audioa);
                        mediaPlayer.start();
                    }
                    return true;
                }  return false;
            }
        });*/

        iglesiaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iglesiaA.setEnabled(false);
                respuesta = respuesta + "a";
                orden.setText(respuesta);

            }
        });

        iglesiaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iglesiaB.setEnabled(false);
                respuesta = respuesta + "b";
                orden.setText(respuesta);
            }
        });

        iglesiaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iglesiaC.setEnabled(false);
                respuesta = respuesta + "c";
                orden.setText(respuesta);
            }
        });
        iglesiaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iglesiaD.setEnabled(false);
                respuesta = respuesta + "d";
                orden.setText(respuesta);
            }
        });


        zuzendu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orden.setText("");
                iglesiaA.setEnabled(true);
                iglesiaB.setEnabled(true);
                iglesiaC.setEnabled(true);
                iglesiaD.setEnabled(true);

                if (respuesta.equals( getString(R.string.ordenBueno))||
                        respuesta.equals(getString(R.string.ordenBuenoDos)) ||
                        respuesta.equals(getString(R.string.ordenBuenoTres))){
                    MediaPlayer mediaPlayer= MediaPlayer.create(OrdenarImagen.this,R.raw.erantzun_zuzena_3_audioa);
                    mediaPlayer.start();
                    orden.setKeyListener(null);
                    //popUp para volver a jugar al juego o ir al menu de juegos
                    Intent popUp = new Intent(OrdenarImagen.this, Popup.class);
                    String valor  = "ordenarImagen";
                    popUp.putExtra("valor", valor );
                    startActivity(popUp);
                }
                else{
                    respuesta = "";
                    MediaPlayer mediaPlayer= MediaPlayer.create(OrdenarImagen.this,R.raw.erantzun_okerra_3_audioa);
                    mediaPlayer.start();
                }
                zuzendu.setClickable(false);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        zuzendu.setClickable(true);

                    }
                }, 3000);
            }
        });
    }
}
