package com.example.didaktikapp;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.didaktikapp.puntos_mapa.Punto0Fragment;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class InicioAudioFragment extends Fragment {

    private InicioAudioViewModel mViewModel;

    private Button btnContinuar;
    private MediaPlayer mediaPlayer;
    private TextView tv;
    String Nombre;
    private int contadorBoton=0;
    public static InicioAudioFragment newInstance() {
        return new InicioAudioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.inicio_audio_fragment, container, false);
        Bundle args = getArguments();
        Nombre = getArguments().getString("nombre");
        //Toast.makeText(getContext(), Nombre, Toast.LENGTH_SHORT).show();


        tv=root.findViewById(R.id.textViewAudio1);
        mediaPlayer= MediaPlayer.create(getActivity(),R.raw.hasiera0_audioa);
        mediaPlayer.start();
        btnContinuar=root.findViewById(R.id.buttonContinuar);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contadorBoton++;

               if (contadorBoton==1){
                   mediaPlayer.stop();
                   tv.setText(getString(R.string.audio2));

                   mediaPlayer= MediaPlayer.create(getActivity(),R.raw.hsiera1_audioa);
                   mediaPlayer.start();

               }

                if (contadorBoton==2){
                    mediaPlayer.stop();
                    Intent intent = new Intent(getActivity(), MikaExplicando.class);
                    intent.putExtra("marcador",0);
                    startActivity(intent);
                }



            }
        });

        return root;
    }

    public void onBackPressed(){
        //Bloquea el boton hacia atras
        getActivity().getSupportFragmentManager().popBackStack();
    }



}
