package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityHutsuneakBete extends AppCompatActivity {

    private Button btnZuzendu;
    private EditText etBaseliza, etBerreraiki, etGurutzearen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hutsuneak_bete);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        etBaseliza=findViewById(R.id.editTextBaseliza);
        etBerreraiki=findViewById(R.id.editTextBerreraiki);
        etGurutzearen=findViewById(R.id.editTextGurutzearen);
        btnZuzendu=findViewById(R.id.buttonZuzendu);





        etGurutzearen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus

                    if (etGurutzearen.getText().toString().equals("gurutzearen")){
                        MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_zuzena4_audioa);
                        mediaPlayer.start();

                    }
                    else{
                        MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_okerra4_audioa);
                        mediaPlayer.start();
                    }
                }
                }

        });


        etBaseliza.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus


                    if (etBaseliza.getText().toString().equals("Landaederreagako Santo Kristo Baseliza")||
                            etBaseliza.getText().toString().equals("Landaederreagako Santo Kristo baseliza") ||
                            etBaseliza.getText().toString().equals("landaederreagako santo kristo baseliza")||
                            etBaseliza.getText().toString().equals("Landaederreagako santo kristo baseliza")){

                        MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_zuzena4_audioa);
                        mediaPlayer.start();
                    }
                    else{
                        MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_okerra4_audioa);
                        mediaPlayer.start();
                    }
                }
            }

        });


        etBerreraiki.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus

                    if (etGurutzearen.getText().toString().equals("berreraiki")){
                        MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_zuzena4_audioa);
                        mediaPlayer.start();

                    }
                    else{
                        MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_okerra4_audioa);
                        mediaPlayer.start();
                    }
                }
            }

        });








        btnZuzendu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((etBaseliza.getText().toString().equals("Landaederreagako Santo Kristo Baseliza")||
                        etBaseliza.getText().toString().equals("Landaederreagako Santo Kristo baseliza") ||
                        etBaseliza.getText().toString().equals("landaederreagako santo kristo baseliza")||
                        etBaseliza.getText().toString().equals("Landaederreagako santo kristo baseliza")) &&
                        etBerreraiki.getText().toString().equals("berreraiki") &&
                        etGurutzearen.getText().toString().equals("gurutzearen")){

                    MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_zuzena4_audioa);
                    mediaPlayer.start();

                }

                else{
                    MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_okerra4_audioa);
                    mediaPlayer.start();

                }





            }
        });








    }
}
