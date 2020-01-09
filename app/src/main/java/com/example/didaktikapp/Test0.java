package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Test0 extends AppCompatActivity {

    private TextView tvA, tvB, tvC, tvGaldera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test0);

        tvA=findViewById(R.id.textViewA);
        tvB=findViewById(R.id.textViewB);
        tvC=findViewById(R.id.textViewC);


        tvA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaPlayer mediaPlayer = MediaPlayer.create(Test0.this, R.raw.erantzun_okerra0_audioa);
                mediaPlayer.start();
            }
        });

        tvB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(Test0.this, R.raw.erantzun_okerra0_audioa);
                mediaPlayer.start();
            }
        });

        tvC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(Test0.this, R.raw.erantzun_zuzena0_audioa);
                mediaPlayer.start();
            }
        });


    }
}
