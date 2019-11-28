package com.example.didaktikapp;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;


public class GurutzegramaFragment extends Fragment {
    //Aqui declaras lo necesario para mostrar el contenido del recyclerView


    private GurutzegramaFragment.OnFragmentInteractionListener mListener;
    private Button  uno;
    private Button  dos;
    private Button  tres;
    private Button  cuatro;
    private Button  cinco;
    private Button  seis;
    private Button  responder;
    private String respuesta;
    private ConstraintLayout constraintLayout;
    private TextView pregunta;
    private String mostrar;
    private EditText respuestaEscrita;
    private ImageView imagenUno;
    private ImageView imagenDos;
    private ImageView imagenTres;
    private ImageView imagenCuatro;
    private ImageView imagenCinco;
    private ImageView imagenSeis;
    private  int contador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gurutzegrama, container, false);
        //Llamamos a lo que necesitemos en este caso es lo siquiente
        uno = root.findViewById(R.id.gurutzegramaUno);
        dos = root.findViewById(R.id.gurutzegramaDos);
        tres = root.findViewById(R.id.gurutzegramaTres);
        cuatro = root.findViewById(R.id.gurutzegramaCuatro);
        cinco = root.findViewById(R.id.gurutzegramaCinco);
        seis = root.findViewById(R.id.gurutzegramaSeis);
        constraintLayout = root.findViewById(R.id.responderGurutzegrama);
        responder = root.findViewById(R.id.responderPregunta);
        pregunta = root.findViewById(R.id.textoPregunta);
        respuestaEscrita = root.findViewById(R.id.escribirRespuesta);
        imagenUno = root.findViewById(R.id.respuestaUno);
        imagenDos = root.findViewById(R.id.respuestaDos);
        imagenTres = root.findViewById(R.id.respuestaTres);
        imagenCuatro = root.findViewById(R.id.respuestaCuatro);
        imagenCinco = root.findViewById(R.id.respuestaCinco);
        imagenSeis = root.findViewById(R.id.respuestaSeis);
        contador = 0;

        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = getString(R.string.respuestaUno);
                constraintLayout.setVisibility(View.VISIBLE);
                pregunta.setText(getString(R.string.verticalUno));
                respuestaEscrita.setText("");
                mostrar = "1";

            }
        });

        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = getString(R.string.respuestaDos);
                constraintLayout.setVisibility(View.VISIBLE);
                pregunta.setText(getString(R.string.horizontalUno));
                respuestaEscrita.setText("");
                mostrar = "2";
            }
        });

        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = getString(R.string.respuestaTres);
                constraintLayout.setVisibility(View.VISIBLE);
                pregunta.setText(getString(R.string.verticalDos));
                respuestaEscrita.setText("");
                mostrar = "3";
            }
        });

        cuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = getString(R.string.respuestaCuatro);
                constraintLayout.setVisibility(View.VISIBLE);
                pregunta.setText(getString(R.string.horizontalDos));
                respuestaEscrita.setText("");
                mostrar = "4";
            }
        });

        cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = getString(R.string.respuestaCinco);
                constraintLayout.setVisibility(View.VISIBLE);
                pregunta.setText(getString(R.string.horizontalTres));
                respuestaEscrita.setText("");
                mostrar = "5";
            }
        });

        seis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta = getString(R.string.respuestaSeis);
                constraintLayout.setVisibility(View.VISIBLE);
                pregunta.setText(getString(R.string.horizontalCuatro));
                respuestaEscrita.setText("");
                mostrar = "6";
            }
        });
        responder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "hola  "+respuestaEscrita.getText().toString());
                Log.d("tag", "adios "+respuesta);
                if(respuestaEscrita.getText().toString().equals(respuesta)){
                    contador++;
                    if(contador == 6){
                        MediaPlayer mediaPlayer= MediaPlayer.create(getActivity(),R.raw.erantzunzuzena6_audioa);
                        mediaPlayer.start();
                    }

                    switch (mostrar){
                        case "1":
                            uno.setVisibility(View.INVISIBLE);
                            imagenUno.setVisibility(View.VISIBLE);
                            break;
                        case "2":
                            dos.setVisibility(View.INVISIBLE);
                            imagenDos.setVisibility(View.VISIBLE);
                            break;
                        case "3":
                            tres.setVisibility(View.INVISIBLE);
                            imagenTres.setVisibility(View.VISIBLE);
                            break;
                        case "4":
                            cuatro.setVisibility(View.INVISIBLE);
                            imagenCuatro.setVisibility(View.VISIBLE);
                            break;
                        case "5":
                            cinco.setVisibility(View.INVISIBLE);
                            imagenCinco.setVisibility(View.VISIBLE);
                            break;
                        case "6":
                            seis.setVisibility(View.INVISIBLE);
                            imagenSeis.setVisibility(View.VISIBLE);
                            break;
                    }
                    constraintLayout.setVisibility(View.INVISIBLE);
                }else{
                    MediaPlayer mediaPlayer= MediaPlayer.create(getActivity(),R.raw.erantzunokerra6_audioa);
                    mediaPlayer.start();
                    constraintLayout.setVisibility(View.INVISIBLE);
                }
            }
        });

        return root;

    }





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String uri);
    }
    @Override
    public void onDetach(){
        super.onDetach();
        mListener=null;
    }


    public void onBackPressed(){
        //Bloquea el boton hacia atras
        getActivity().getSupportFragmentManager().popBackStack();
    }



}