package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TestGalderak extends AppCompatActivity {
    ArrayList<TestGalderak.Pregunta> preguntas = new ArrayList<>();
    int posicion;
    String preg;
    String respuesta1;
    String respuesta2;
    String respuesta3;
    String correcto;
    Button respu1;
    Button respu2;
    Button respu3;
    TextView pregunta;
    TextView indicador;
    int puntos = 0;
    int puntosCorrectos=0;
    MediaPlayer mediaPlayerzuzena;
    MediaPlayer mediaPlayerokerra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_galderak);
        pregunta = findViewById(R.id.testPregunta);
        respu1 = findViewById(R.id.respuestaTest1);
        //Onclick de los botones respuesta
        respu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta(respu1.getText().toString());
            }
        });
        respu2 = findViewById(R.id.respuestaTest2);
        respu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta(respu2.getText().toString());
            }
        });
        respu3 = findViewById(R.id.respuestaTest3);
        respu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta(respu3.getText().toString());
            }
        });
        indicador = findViewById(R.id.numeroPregunta);
        Crearpreguntas();
        seleccionarPregunta();
    }
    //Creamos las preguntas con sus opciones y su respuesta
    public void Crearpreguntas(){
        TestGalderak.Pregunta P = new TestGalderak.Pregunta(getString(R.string.testPreguntaUno),getString(R.string.testRespuestaUnoUno),getString(R.string.testRespuestaUnoDos),getString(R.string.testRespuestaUnoTres),getString(R.string.testRespuestaUnoDos));
        preguntas.add(P);
        TestGalderak.Pregunta P1 = new TestGalderak.Pregunta(getString(R.string.testPreguntaDos),getString(R.string.testRespuestaDosUno),getString(R.string.testRespuestaDosDos),getString(R.string.testRespuestaDosTres),getString(R.string.testRespuestaDosUno));
        preguntas.add(P1);
        TestGalderak.Pregunta P2 = new TestGalderak.Pregunta(getString(R.string.testPreguntaTres),getString(R.string.testRespuestaTresUno),getString(R.string.testRespuestaTresDos),getString(R.string.testRespuestaTresTres),getString(R.string.testRespuestaTresTres));
        preguntas.add(P2);
        TestGalderak.Pregunta P3 = new TestGalderak.Pregunta(getString(R.string.testPreguntaCuatro),getString(R.string.testRespuestaCuatroUno),getString(R.string.testRespuestaCuatroDos),getString(R.string.testRespuestaCuatroTres),getString(R.string.testRespuestaCuatroDos));
        preguntas.add(P3);
    }
    //Seleccionamos una de las preguntas al azar sin repetirla
    public void seleccionarPregunta(){

        if (preguntas.size() == 4) {
            posicion = (int) (Math.random() * 4);
            preg = preguntas.get(posicion).getPregunta();
            respuesta1 = preguntas.get(posicion).getRes1();
            respuesta2 = preguntas.get(posicion).getRes2();
            respuesta3 = preguntas.get(posicion).getRes3();
            correcto = preguntas.get(posicion).getResCorrecta();
            pregunta.setText(preg);
            respu1.setText(respuesta1);
            respu2.setText(respuesta2);
            respu3.setText(respuesta3);
            preguntas.remove(posicion);
        }else if (preguntas.size() == 3) {
            posicion = (int) (Math.random() * 3);
            preg = preguntas.get(posicion).getPregunta();
            respuesta1 = preguntas.get(posicion).getRes1();
            respuesta2 = preguntas.get(posicion).getRes2();
            respuesta3 = preguntas.get(posicion).getRes3();
            correcto = preguntas.get(posicion).getResCorrecta();
            pregunta.setText(preg);
            respu1.setText(respuesta1);
            respu2.setText(respuesta2);
            respu3.setText(respuesta3);
            preguntas.remove(posicion);
        }else if (preguntas.size() ==2 ){
            posicion = (int) (Math.random() * 2);
            preg = preguntas.get(posicion).getPregunta();
            respuesta1 = preguntas.get(posicion).getRes1();
            respuesta2 = preguntas.get(posicion).getRes2();
            respuesta3 = preguntas.get(posicion).getRes3();
            correcto = preguntas.get(posicion).getResCorrecta();
            pregunta.setText(preg);
            respu1.setText(respuesta1);
            respu2.setText(respuesta2);
            respu3.setText(respuesta3);
            preguntas.remove(posicion);
        }else if (preguntas.size()==1){
            preg = preguntas.get(0).getPregunta();
            respuesta1 = preguntas.get(0).getRes1();
            respuesta2 = preguntas.get(0).getRes2();
            respuesta3 = preguntas.get(0).getRes3();
            correcto = preguntas.get(0).getResCorrecta();
            pregunta.setText(preg);
            respu1.setText(respuesta1);
            respu2.setText(respuesta2);
            respu3.setText(respuesta3);
            preguntas.remove(0);
        }

    }
    //Actualizamos la parte superior derecha para saber en que pregunta estamos
    public void respuesta(String cadena){
        if (cadena.equals(correcto)) {
            puntos = puntos +1;
            puntosCorrectos++;

            mediaPlayerzuzena= MediaPlayer.create(getApplicationContext(),R.raw.erantzun_zuzena_5_audioa);
            mediaPlayerzuzena.start();

            respu1.setClickable(false);
            respu2.setClickable(false);
            respu3.setClickable(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {

                    respu1.setClickable(true);
                    respu2.setClickable(true);
                    respu3.setClickable(true);

                }
            }, 2000);

            seleccionarPregunta();
            if (indicador.getText().toString().equals(getString(R.string.numeroPreguntaTestUno))){
                indicador.setText(getString(R.string.numeroPreguntaTestDos));
            }else if(indicador.getText().toString().equals(getString(R.string.numeroPreguntaTestDos))){
                indicador.setText(getString(R.string.numeroPreguntaTestTres));
            }else if(indicador.getText().toString().equals(getString(R.string.numeroPreguntaTestTres))){
                indicador.setText(getString(R.string.numeroPreguntaTestCuatro));
            }
            else if(indicador.getText().toString().equals(getString(R.string.numeroPreguntaTestCuatro))){

            }

            if (puntosCorrectos==4){

                respu1.setClickable(false);
                respu2.setClickable(false);
                respu3.setClickable(false);


                try {
                    if (getIntent().getStringExtra("valor").equals("historia6")){

                        Intent popUp = new Intent(TestGalderak.this, Popup.class);
                        String valor  = "testgalderak6historia";
                        popUp.putExtra("valor", valor );
                        startActivity(popUp);
                    }
                }
                catch (Exception e){
                    Intent popUp = new Intent(TestGalderak.this, Popup.class);
                    String valor  = "test";
                    popUp.putExtra("valor", valor );
                    startActivity(popUp);
                }






            }

        }else{
            puntos = puntos-1;
            mediaPlayerokerra= MediaPlayer.create(getApplicationContext(),R.raw.erantzun_okerra_5_audioa);
            mediaPlayerokerra.start();
            respu1.setClickable(false);
            respu2.setClickable(false);
            respu3.setClickable(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {

                    respu1.setClickable(true);
                    respu2.setClickable(true);
                    respu3.setClickable(true);

                }
            }, 3000);

        }

    }

    public class Pregunta {


        public String getPregunta() {
            return pregunta;
        }

        public String getRes1() {
            return res1;
        }

        public String getRes2() {
            return res2;
        }

        public String getRes3() {
            return res3;
        }

        public String getResCorrecta() {
            return resCorrecta;
        }

        String pregunta;
        String res1;
        String res2;
        String res3;
        String resCorrecta;

        public  Pregunta(String preg,String R1,String R2,String R3,String RC) {
            this.pregunta = preg;
            this.res1 = R1;
            this.res2 = R2;
            this.res3 = R3;
            this.resCorrecta = RC;
        }

    }
}
