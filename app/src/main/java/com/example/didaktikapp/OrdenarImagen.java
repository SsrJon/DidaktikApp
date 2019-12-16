package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class OrdenarImagen extends AppCompatActivity {

    private EditText orden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenar_imagen);
        orden = findViewById(R.id.ordenRespuesta);
       //comprobamos que lo escrito coincida con la respuesta correcta
        orden.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if (orden.getText().toString().equals( getString(R.string.ordenBueno))||
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
                }
                return false;
            }
        });
    }
}
