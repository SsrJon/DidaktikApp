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
    private ImageView imgTabla;
    private TextView tvAudio;
    private int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hutsuneak_tabla);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        etOndarearen=findViewById(R.id.editTextOndarearen);
        etEgilea=findViewById(R.id.editTextEgilea);
        etHeldu=findViewById(R.id.editTextHeldu);
        etKultural=findViewById(R.id.editTextKultural);

        imgTabla=findViewById(R.id.imageViewTabla);
        tvAudio=findViewById(R.id.textViewAudio);

        btnZuzendu=findViewById(R.id.buttonZuzendu);

        imgTabla.setVisibility(View.INVISIBLE);
        etOndarearen.setVisibility(View.INVISIBLE);
        etEgilea.setVisibility(View.INVISIBLE);
        etHeldu.setVisibility(View.INVISIBLE);
        etKultural.setVisibility(View.INVISIBLE);


        MediaPlayer mediaPlayer = MediaPlayer.create(HutsuneakTabla.this, R.raw.larrea_eskultura0_audioa);
        mediaPlayer.start();

        btnZuzendu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnZuzendu.setText("Zuzendu");
                tvAudio.setVisibility(View.INVISIBLE);
                contador++;



                imgTabla.setVisibility(View.VISIBLE);
                etOndarearen.setVisibility(View.VISIBLE);
                etEgilea.setVisibility(View.VISIBLE);
                etHeldu.setVisibility(View.VISIBLE);
                etKultural.setVisibility(View.VISIBLE);

                if(contador>1) {


                    if ((etOndarearen.getText().toString().equals("Larrea eskultura") ||
                            etOndarearen.getText().toString().equals("Larrea Eskultura") ||
                            etOndarearen.getText().toString().equals("larrea eskultura") ||
                            etOndarearen.getText().toString().equals("LARREA ESKULTURA")) &&
                            (etEgilea.getText().toString().equals("Vicente Larrea") ||
                                    etEgilea.getText().toString().equals("Vicente larrea") ||
                                    etEgilea.getText().toString().equals("vicente larrea") ||
                                    etEgilea.getText().toString().equals("VICENTE LARREA")) &&
                            (etHeldu.getText().toString().equals("2002an") ||
                                    etHeldu.getText().toString().equals("2002")) &&
                            (etKultural.getText().toString().equals("Ondare kultural arkitektonikoa") ||
                                    etKultural.getText().toString().equals("Ondare Kultural Arkitektonikoa") ||
                                    etKultural.getText().toString().equals("ondare kultural arkitektonikoa") ||
                                    etKultural.getText().toString().equals("ONDARE KULTURAL ARKITEKTONIKOA"))) {

                        MediaPlayer mediaPlayer = MediaPlayer.create(HutsuneakTabla.this, R.raw.erantzun_zuzena1_audioa);
                        mediaPlayer.start();

                        Intent popUp = new Intent(HutsuneakTabla.this, PopupHorizontal.class);
                        String valor  = "tabla";
                        startActivity(popUp);


                    } else {
                        MediaPlayer mediaPlayer = MediaPlayer.create(HutsuneakTabla.this, R.raw.erantzun_okerra1_audioa);
                        mediaPlayer.start();
                    }

                }

            }
        });



    }
}
