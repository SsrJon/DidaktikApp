package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Test0 extends AppCompatActivity {

    private TextView tvA, tvB, tvC;
    private ImageView imgMal, imgMal2, imgBien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test0);

        tvA=findViewById(R.id.textViewA);
        tvB=findViewById(R.id.textViewB);
        tvC=findViewById(R.id.textViewC);

        imgMal=findViewById(R.id.imageViewMal);
        imgMal2=findViewById(R.id.imageViewMal2);
        imgBien=findViewById(R.id.imageViewBien);

        tvA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaPlayer mediaPlayer = MediaPlayer.create(Test0.this, R.raw.erantzun_okerra0_audioa);
                mediaPlayer.start();
                tvA.setClickable(false);
                tvB.setClickable(false);
                tvC.setClickable(false);

                imgMal.setVisibility(View.VISIBLE);

                delayRespuesta();
            }
        });

        tvB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(Test0.this, R.raw.erantzun_okerra0_audioa);
                mediaPlayer.start();
                tvA.setClickable(false);
                tvB.setClickable(false);
                tvC.setClickable(false);
                imgMal2.setVisibility(View.VISIBLE);
                delayRespuesta();
            }
        });

        tvC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(Test0.this, R.raw.erantzun_zuzena0_audioa);
                mediaPlayer.start();
                tvA.setClickable(false);
                tvB.setClickable(false);
                tvC.setClickable(false);
                imgBien.setVisibility(View.VISIBLE);
                delayRespuesta();

                Intent popUp = new Intent(Test0.this, Popup.class);
                String valor  = "test0";
                popUp.putExtra("valor", valor );
                startActivity(popUp);
            }
        });


    }


    private void delayRespuesta(){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                tvA.setClickable(true);
                tvB.setClickable(true);
                tvC.setClickable(true);

                imgMal.setVisibility(View.INVISIBLE);
                imgMal2.setVisibility(View.INVISIBLE);
                imgBien.setVisibility(View.INVISIBLE);

            }
        }, 3000);
    }
}
