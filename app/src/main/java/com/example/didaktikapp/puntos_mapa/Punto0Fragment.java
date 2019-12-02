package com.example.didaktikapp.puntos_mapa;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.didaktikapp.R;

public class Punto0Fragment extends Fragment {

    private Punto0ViewModel mViewModel;

    public static Punto0Fragment newInstance() {
        return new Punto0Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.punto0_fragment, container, false);





        return root;
    }


}
