package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class HutsuneakTabla extends AppCompatActivity {

    private EditText etOndarearen, etEgilea, etHeldu, etKultural;
    private Button btnZuzendu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hutsuneak_tabla);

        etOndarearen=findViewById(R.id.editTextOndarearen);
        etEgilea=findViewById(R.id.editTextEgilea);
        etHeldu=findViewById(R.id.editTextHeldu);
        etKultural=findViewById(R.id.editTextKultural);

        btnZuzendu=findViewById(R.id.buttonZuzendu);


        btnZuzendu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Si los datos son correctos
                    if ((etOndarearen.getText().toString().toUpperCase().equals("LARREA ESKULTURA")
                        &&
                            (etEgilea.getText().toString().toUpperCase().equals("VICENTE LARREA")
                                  &&
                            (etHeldu.getText().toString().toUpperCase().equals("2002AN") ||
                                    etHeldu.getText().toString().equals("2002")) &&
                            (etKultural.getText().toString().toUpperCase().equals("ONDARE KULTURAL ARKITEKTONIKOA") ||
                                    etKultural.getText().toString().toUpperCase().equals("ARKITEKTONIKOA") ||
                                    etKultural.getText().toString().toUpperCase().equals("KULTURAL ARKITEKTONIKOA") 
                            )))) {

                        MediaPlayer mediaPlayer = MediaPlayer.create(HutsuneakTabla.this, R.raw.erantzun_zuzena1_audioa);
                        mediaPlayer.start();


                        //Intent al popup, el primero se ejecuta cuando estas haciendo el recorrido (desde el mapa),
                        //el segundo cuando entras desde el menú de juegos

                        try {
                            if (getIntent().getStringExtra("valor").equals("historia0")){

                                Intent popUp = new Intent(HutsuneakTabla.this, Popup.class);
                                String valor  = "tabla";
                                popUp.putExtra("valor", valor );
                                startActivity(popUp);
                            }
                        }
                        catch (Exception e){
                            Intent popUp = new Intent(HutsuneakTabla.this, Popup.class);
                            String valor  = "tablaLibre";
                            popUp.putExtra("valor", valor );
                            startActivity(popUp);
                        }

                    } else {
                        MediaPlayer mediaPlayer = MediaPlayer.create(HutsuneakTabla.this, R.raw.erantzun_okerra1_audioa);
                        mediaPlayer.start();
                    }



            }
        });



    }
}
