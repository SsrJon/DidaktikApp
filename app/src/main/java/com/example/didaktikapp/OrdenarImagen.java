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
        /*orden.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    if (orden.getText().toString().equals( getString(R.string.ordenBueno))||
                            orden.getText().toString().equals(getString(R.string.ordenBuenoDos)) ||
                            orden.getText().toString().equals(getString(R.string.ordenBuenoTres))){
                        MediaPlayer mediaPlayer= MediaPlayer.create(OrdenarImagen.this,R.raw.erantzun_zuzena_3_audioa);
                        mediaPlayer.start();
                        //etBaseliza.setEnabled(false);
                        orden.setKeyListener(null);
                    }
                    else{
                        MediaPlayer mediaPlayer= MediaPlayer.create(OrdenarImagen.this,R.raw.erantzun_okerra_3_audioa);
                        mediaPlayer.start();
                    }
                }

            }

        });*/
        orden.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if (orden.getText().toString().equals( getString(R.string.ordenBueno))||
                            orden.getText().toString().equals(getString(R.string.ordenBuenoDos)) ||
                            orden.getText().toString().equals(getString(R.string.ordenBuenoTres))){
                        MediaPlayer mediaPlayer= MediaPlayer.create(OrdenarImagen.this,R.raw.erantzun_zuzena_3_audioa);
                        mediaPlayer.start();
                        orden.setKeyListener(null);
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
