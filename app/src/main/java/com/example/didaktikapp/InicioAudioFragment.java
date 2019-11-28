package com.example.didaktikapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class InicioAudioFragment extends Fragment {

    private InicioAudioViewModel mViewModel;

    private Button btnContinuar;
    private MediaPlayer mediaPlayer;

    public static InicioAudioFragment newInstance() {
        return new InicioAudioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.inicio_audio_fragment, container, false);


        mediaPlayer= MediaPlayer.create(getActivity(),R.raw.hasiera0_audioa);
        mediaPlayer.start();


        btnContinuar=root.findViewById(R.id.buttonContinuar);

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.stop();
                Intent intent = new Intent(getActivity(), MapaActivity.class);
                startActivity(intent);


            }
        });

        return root;
    }



}
