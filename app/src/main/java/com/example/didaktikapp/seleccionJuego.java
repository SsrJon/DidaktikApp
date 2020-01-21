package com.example.didaktikapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class seleccionJuego extends AppCompatActivity {
    AdaptadorJuegos adap;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);
        rellenarJuegos();
        adap = new AdaptadorJuegos (Juegos.getJuegosArrayList());
        adap.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println(recyclerView.getChildAdapterPosition(v));
                String fotoaMandar  = Juegos.getJuegosArrayList().get(recyclerView.getChildAdapterPosition(v)).getNombre();
               //Todas las condiciones para añadir el intent dependiendo que juegos es.
                if(fotoaMandar.equals("Quiz")){
                    Intent intent = new Intent(seleccionJuego.this, Quiz.class);
                    startActivity(intent);
                }if (fotoaMandar.equals("Puzzle")){
                   // Intent intent = new Intent(seleccionJuego.this, PuzzleActivity.class);
                    //startActivity(intent);
                }if (fotoaMandar.equals("Puzzle irristagarria")){
                    Intent intent = new Intent(seleccionJuego.this, com.example.didaktikapp.Puzzle_cuadrados.PuzzleActivity.class);
                    startActivity(intent);
                }if (fotoaMandar.equals("Hutsuneak bete")){
                    Intent intent = new Intent(seleccionJuego.this, ActivityHutsuneakBete.class);
                    startActivity(intent);
                }
                if (fotoaMandar.equals("Hutsuneak bete2")){
                    Intent intent = new Intent(seleccionJuego.this, ActivityHutsuneakBete2.class);
                    startActivity(intent);
                }
                if (fotoaMandar.equals("Gurutzegrama")){
                    Intent intent = new Intent(seleccionJuego.this, GurutzegramaActivity.class);
                    startActivity(intent);

                }if (fotoaMandar.equals("Egia/Gezurra")){
                    Intent intent = new Intent(seleccionJuego.this, EgiaedoGezurra.class);
                    startActivity(intent);
                }if(fotoaMandar.equals("Argazkia aukeratu")) {
                    Intent intent = new Intent(seleccionJuego.this, FotoElegir.class);
                    startActivity(intent);
                }if(fotoaMandar.equals("Larrea 1")) {
                    Intent intent = new Intent(seleccionJuego.this, HutsuneakTabla.class);
                    startActivity(intent);
                }
                if (fotoaMandar.equals("Ordenatu Kronologikoki")){
                    Intent intent = new Intent(seleccionJuego.this, OrdenarImagen.class);
                    startActivity(intent);
                }
                if (fotoaMandar.equals("Test")){
                    Intent intent = new Intent(seleccionJuego.this, TestGalderak.class);
                    startActivity(intent);
                }
                if (fotoaMandar.equals("Taula")){
                    Intent intent = new Intent(seleccionJuego.this, HutsuneakTabla.class);
                    startActivity(intent);
                }
                if (fotoaMandar.equals("Test0")){
                    Intent intent = new Intent(seleccionJuego.this, Test0.class);
                    startActivity(intent);
                }
                if(fotoaMandar.equals("Sopilla")){
                    Intent intent = new Intent(seleccionJuego.this, WordSearchActivity.class);
                    startActivity(intent);
                }

            }
        });
        recyclerView = findViewById(R.id.ReciclerView);
        recyclerView.setAdapter(adap);
        layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void rellenarJuegos(){
        Juegos.getJuegosArrayList().clear();
        //Juegos J = new Juegos("Sopa de letras",getDrawable(R.drawable.sopa));
        //Juegos.getJuegosArrayList().add(J);
        Juegos J2 = new Juegos("Quiz",getDrawable(R.drawable.quiz));
        Juegos.getJuegosArrayList().add(J2);
        Juegos J3 = new Juegos("Gurutzegrama",getDrawable(R.drawable.cruzada));
        Juegos.getJuegosArrayList().add(J3);
        Juegos J4 = new Juegos("Puzzle",getDrawable(R.drawable.puzzle));
        Juegos.getJuegosArrayList().add(J4);
        Juegos J5 = new Juegos("Muztioa egin",getDrawable(R.drawable.muztio));
        Juegos.getJuegosArrayList().add(J5);
        Juegos J6 = new Juegos("Puzzle irristagarria",getDrawable(R.drawable.puzzletearrastro));
        Juegos.getJuegosArrayList().add(J6);
        Juegos J7 = new Juegos("Hutsuneak bete",getDrawable(R.drawable.rellenar_hueco));
        Juegos.getJuegosArrayList().add(J7);
        Juegos J8 = new Juegos("Egia/Gezurra",getDrawable(R.drawable.egiagezurra));
        Juegos.getJuegosArrayList().add(J8);
        Juegos J9 = new Juegos("Argazkia aukeratu",getDrawable(R.drawable.iglesiaicon));
        Juegos.getJuegosArrayList().add(J9);
        Juegos J10 = new Juegos("Ordenatu Kronologikoki",getDrawable(R.drawable.cronologia));
        Juegos.getJuegosArrayList().add(J10);
        Juegos J11 = new Juegos("Test",getDrawable(R.drawable.test));
        Juegos.getJuegosArrayList().add(J11);
        Juegos J12 = new Juegos("Taula",getDrawable(R.drawable.tabla));
        Juegos.getJuegosArrayList().add(J12);
        Juegos J13 = new Juegos("Hutsuneak bete2",getDrawable(R.drawable.rellenar_hueco));
        Juegos.getJuegosArrayList().add(J13);
        Juegos J14 = new Juegos("Test0",getDrawable(R.drawable.test));
        Juegos.getJuegosArrayList().add(J14);
        Juegos J15 = new Juegos("Sopilla",getDrawable(R.drawable.rellenar_hueco));
        Juegos.getJuegosArrayList().add(J15);


    }
    public void onBackPressed(){
        Intent intent = new Intent(seleccionJuego.this,MapaActivity.class);
        startActivity(intent);
    }
}
