package com.example.didaktikapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {
    ArrayList<Pregunta> preguntas = new ArrayList<>();
    int posicion;
    String preg;
    String res1;
    String res2;
    String res3;
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
        setContentView(R.layout.activity_quiz);
        pregunta = findViewById(R.id.pregunta);
        respu1 = findViewById(R.id.res1);
        respu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta(respu1.getText().toString());
            }
        });
        respu2 = findViewById(R.id.res2);
        respu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta(respu2.getText().toString());
            }
        });
        respu3 = findViewById(R.id.res3);
        respu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta(respu3.getText().toString());
            }
        });
        indicador = findViewById(R.id.indicador);
        Crearpreguntas();
        seleccionarPregunta();
    }
    //Creamos las preguntas con sus opciones y su respuesta
    public void Crearpreguntas(){
        Pregunta P = new Pregunta(getString(R.string.quizPreguntaUno),getString(R.string.quizrespuestaUnoUno),getString(R.string.quizrespuestaUnoDos),getString(R.string.quizrespuestaUnoTres),getString(R.string.quizrespuestaUnoTres));
        preguntas.add(P);
        Pregunta P1 = new Pregunta(getString(R.string.quizPreguntaUno),getString(R.string.quizrespuestaDosUno),getString(R.string.quizrespuestaDosDos),getString(R.string.quizrespuestaDosTres),getString(R.string.quizrespuestaDosUno));
        preguntas.add(P1);
        Pregunta P2 = new Pregunta(getString(R.string.quizPreguntaTres),getString(R.string.quizrespuestaTresUno),getString(R.string.quizrespuestaTresDos),getString(R.string.quizrespuestaTresTres),getString(R.string.quizrespuestaTresUno));
        preguntas.add(P2);
    }
    //Seleccionamos una de las preguntas al azar sin repetirla
    public void seleccionarPregunta(){
        if (preguntas.size() == 3) {
            posicion = (int) (Math.random() * 3);
            preg = preguntas.get(posicion).getPregunta();
            res1 = preguntas.get(posicion).getRes1();
            res2 = preguntas.get(posicion).getRes2();
            res3 = preguntas.get(posicion).getRes3();
            correcto = preguntas.get(posicion).getResCorrecta();
            pregunta.setText(preg);
            respu1.setText(res1);
            respu2.setText(res2);
            respu3.setText(res3);
            preguntas.remove(posicion);
        }else if (preguntas.size() ==2 ){
            posicion = (int) (Math.random() * 2);
            preg = preguntas.get(posicion).getPregunta();
            res1 = preguntas.get(posicion).getRes1();
            res2 = preguntas.get(posicion).getRes2();
            res3 = preguntas.get(posicion).getRes3();
            correcto = preguntas.get(posicion).getResCorrecta();
            pregunta.setText(preg);
            respu1.setText(res1);
            respu2.setText(res2);
            respu3.setText(res3);
            preguntas.remove(posicion);
        }else if (preguntas.size()==1){
            preg = preguntas.get(0).getPregunta();
            res1 = preguntas.get(0).getRes1();
            res2 = preguntas.get(0).getRes2();
            res3 = preguntas.get(0).getRes3();
            correcto = preguntas.get(0).getResCorrecta();
            pregunta.setText(preg);
            respu1.setText(res1);
            respu2.setText(res2);
            respu3.setText(res3);
            preguntas.remove(0);
        }

    }
    //Actualizamos la parte superior derecha para saber en que pregunta estamos
    public void respuesta(String cadena){
        if (cadena.equals(correcto)) {
            puntos = puntos +1;
            puntosCorrectos++;

            mediaPlayerzuzena= MediaPlayer.create(getApplicationContext(),R.raw.erantzun_zuzena_3_audioa);
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
            if (indicador.getText().toString().equals(getString(R.string.numeroPreguntaQuizUno))){
                indicador.setText(getString(R.string.numeroPreguntaQuizDos));
            }else if(indicador.getText().toString().equals(getString(R.string.numeroPreguntaQuizDos))){
                indicador.setText(getString(R.string.numeroPreguntaQuizTres));
            }else if(indicador.getText().toString().equals(getString(R.string.numeroPreguntaQuizTres))){

            }

            if (puntosCorrectos==3){

                respu1.setClickable(false);
                respu2.setClickable(false);
                respu3.setClickable(false);

                try {
                    if (getIntent().getStringExtra("valor").equals("historia3")){

                        Intent popUp = new Intent(Quiz.this, Popup.class);
                        String valor  = "quiz3historia";
                        popUp.putExtra("valor", valor );
                        startActivity(popUp);
                    }
                }
                catch (Exception e){
                    Intent popUp = new Intent(Quiz.this, Popup.class);
                    String valor  = "quiz";
                    popUp.putExtra("valor", valor );
                    startActivity(popUp);
                }

            }

        }else{
            puntos = puntos-1;
            mediaPlayerokerra= MediaPlayer.create(getApplicationContext(),R.raw.erantzun_okerra_3_audioa);
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
