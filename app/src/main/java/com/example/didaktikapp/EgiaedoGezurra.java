package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EgiaedoGezurra extends AppCompatActivity {
    ArrayList<EgiaedoGezurra.Pregunta> preguntas = new ArrayList<>();
    Button Verdad;
    Button Mentir;
    TextView pregun;
    int contador = 0;
    int puntuacion = 0;
    ConstraintLayout fin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egiaedo_gezurra);
        Verdad = findViewById(R.id.BTNegia);
        Verdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botones(Verdad.getText().toString());
            }
        });
        Mentir = findViewById(R.id.BTNmentira);
        Mentir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botones(Mentir.getText().toString());
            }
        });
        pregun = findViewById(R.id.Pregunta);
        //fin = findViewById(R.id.Final);
        Crearpreguntas();
    }

    public class Pregunta {

        public String getPregunta() {
            return pregunta;
        }

        public String getResCorrecta() {
            return resCorrecta;
        }

        String pregunta;
        String resCorrecta;

        public  Pregunta(String preg,String RC) {
            this.pregunta = preg;
            this.resCorrecta = RC;
        }

    }

    public void Crearpreguntas(){
        EgiaedoGezurra.Pregunta P = new EgiaedoGezurra.Pregunta(getString(R.string.EGpreguntaUno),getString(R.string.egia));
        preguntas.add(P);
        EgiaedoGezurra.Pregunta P1 = new EgiaedoGezurra.Pregunta(getString(R.string.EGpreguntaDos),getString(R.string.gezurra));
        preguntas.add(P1);
        EgiaedoGezurra.Pregunta P2 = new EgiaedoGezurra.Pregunta(getString(R.string.EGpreguntaTres),getString(R.string.gezurra));
        preguntas.add(P2);
        EgiaedoGezurra.Pregunta P3 = new EgiaedoGezurra.Pregunta(getString(R.string.EGpreguntaCuatro),getString(R.string.egia));
        preguntas.add(P3);
    }

    public void botones(String respuesta){
        if (respuesta.equals(preguntas.get(contador).getResCorrecta())){
                puntuacion = puntuacion +1;

        }
        if (contador < preguntas.size()-1){
            contador = contador + 1;
        }
        if (contador >= preguntas.size()-1) {



            try {
                if (getIntent().getStringExtra("valor").equals("historia3")){

                    Intent popUp = new Intent(EgiaedoGezurra.this, PopupHorizontal.class);
                    String valor  = "egia3historia";
                    popUp.putExtra("valor", valor );
                    startActivity(popUp);
                }
            }
            catch (Exception e){
                Intent popUp = new Intent(EgiaedoGezurra.this, PopupHorizontal.class);
                String valor  = "egia";
                popUp.putExtra("valor", valor );
                startActivity(popUp);
            }





        }

        pregun.setText(preguntas.get(contador).getPregunta());


    }
}
