package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ActivityHutsuneakBete extends AppCompatActivity {

    private Button btnJarraitu;
    private EditText etBaseliza, etBerreraiki, etGurutzearen;
    private ImageView imgZuzena1, imgZuzena2, imgZuzena3, imgOkerra1, imgOkerra2, imgOkerra3 ;
    int contadorButton=0;
    int contadorGurutze=0;
    int contadorBerreraiki=0;
    int contadorBaseliza=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_hutsuneak_bete);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        etBaseliza=findViewById(R.id.editTextBaseliza);
        etBerreraiki=findViewById(R.id.editTextBerreraiki);
        etGurutzearen=findViewById(R.id.editTextGurutzearen);

        imgZuzena1=findViewById(R.id.imageViewZuzena1);
        imgZuzena2=findViewById(R.id.imageViewZuzena2);
        imgZuzena3=findViewById(R.id.imageViewZuzena3);
        imgOkerra1=findViewById(R.id.imageViewOkerra1);
        imgOkerra2=findViewById(R.id.imageViewOkerra2);
        imgOkerra3=findViewById(R.id.imageViewOkerra3);

        btnJarraitu=findViewById(R.id.buttonJarraitu);


        //Se activa al quitar el focus
        etBaseliza.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus

                    comprobarBaseliza();


                }
            }

        });

        //Se activa al darle enter en el teclado
        etBaseliza.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    comprobarBaseliza();

                    return true;
                }
                return false;
            }
        });

        //Se activa al quitar el focus
        etBerreraiki.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    comprobarBerreraiki();

                }
            }

        });

        //Se activa al darle enter en el teclado
        etBerreraiki.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    comprobarBerreraiki();

                    return true;
                }
                return false;
            }
        });



        //Se activa al darle enter en el teclado
        etGurutzearen.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    comprobarGurutzearen();

                    return true;
                }
                return false;
            }
        });

        //Se activa al quitar el focus
        etGurutzearen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    comprobarGurutzearen();

                }
            }

        });


        btnJarraitu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //programar una accion aqui

            }
        });




    }



    @Override
    public void onBackPressed () {
        //Bloquea el boton hacia atras
    }


    //comprueba el contador para hacer visible el boton jarraitu
    private void Jarraitu(){

        if (contadorButton==3){

            Intent popUp = new Intent(ActivityHutsuneakBete.this, Popup.class);
            startActivity(popUp);
            //System.out.println(contadorButton);
            //btnJarraitu.setVisibility(View.VISIBLE);

        }
    }

    //comprueba  la respuesta del editText Gurutzearen
    private void comprobarGurutzearen(){

        if(contadorGurutze==0) {
            if (etGurutzearen.getText().toString().equals("gurutzearen")) {
                imgZuzena1.setVisibility(View.VISIBLE);
                imgOkerra1.setVisibility(View.INVISIBLE);
                MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete.this, R.raw.erantzun_zuzena4_audioa);
                mediaPlayer.start();
                //etGurutzearen.setEnabled(false);
                etGurutzearen.setKeyListener(null);
                contadorButton++;
                System.out.println(contadorButton);
                Jarraitu();
                contadorGurutze++;


            } else {
                imgZuzena1.setVisibility(View.INVISIBLE);
                imgOkerra1.setVisibility(View.VISIBLE);
                MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete.this, R.raw.erantzun_okerra4_audioa);
                mediaPlayer.start();
            }
        }


    }

    //comprueba  la respuesta del editText de berreraiki
    private void comprobarBerreraiki(){

        if(contadorBerreraiki==0) {

            if (etBerreraiki.getText().toString().equals("berreraiki")){
                imgZuzena3.setVisibility(View.VISIBLE);
                imgOkerra3.setVisibility(View.INVISIBLE);
                MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_zuzena4_audioa);
                mediaPlayer.start();
                //etBerreraiki.setEnabled(false);
                etBerreraiki.setKeyListener(null);
                contadorButton++;
                System.out.println(contadorButton);
                Jarraitu();
                contadorBerreraiki++;

            }
            else{
                imgZuzena3.setVisibility(View.INVISIBLE);
                imgOkerra3.setVisibility(View.VISIBLE);
                MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_okerra4_audioa);
                mediaPlayer.start();
            }

        }

    }

    //comprueba  la respuesta del editText de baseliza
    private void comprobarBaseliza(){

        if(contadorBaseliza==0) {

            if (etBaseliza.getText().toString().equals("Landaederreagako Santo Kristo Baseliza")||
                    etBaseliza.getText().toString().equals("Landaederreagako Santo Kristo baseliza") ||
                    etBaseliza.getText().toString().equals("landaederreagako santo kristo baseliza")||
                    etBaseliza.getText().toString().equals("Landaederreagako santo kristo baseliza")){

                imgZuzena2.setVisibility(View.VISIBLE);
                imgOkerra2.setVisibility(View.INVISIBLE);
                MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_zuzena4_audioa);
                mediaPlayer.start();
                //etBaseliza.setEnabled(false);
                etBaseliza.setKeyListener(null);
                contadorButton++;
                System.out.println(contadorButton);
                Jarraitu();
                contadorBaseliza++;

            }
            else{
                imgZuzena2.setVisibility(View.INVISIBLE);
                imgOkerra2.setVisibility(View.VISIBLE);
                MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_okerra4_audioa);
                mediaPlayer.start();
            }

        }
    }








}

