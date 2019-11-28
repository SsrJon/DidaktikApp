package com.example.didaktikapp;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InicioFragment extends Fragment {

    private Button btnEmpezar,btnPruebas;
    private EditText etNombreInicio;


    private InicioViewModel mViewModel;

    public static InicioFragment newInstance() {
        return new InicioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.inicio_fragment, container, false);




        btnEmpezar=root.findViewById(R.id.buttonEmpezar);
        etNombreInicio=root.findViewById(R.id.editTextNombreInicio);


        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!etNombreInicio.getText().toString().equals("")){
                    //el fragment se reemplaza
                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, new InicioAudioFragment());
                    fragmentTransaction.commit();
                }

                else {

                    System.out.println("Error");
                }


            }
        });



        btnPruebas=root.findViewById(R.id.buttonPruebas);

        btnPruebas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),ActivityHutsuneakBete.class);
                startActivity(intent);

            }
        });








        return root;

    }


    public void onBackPressed(){
        //Bloquea el boton hacia atras
        getActivity().getSupportFragmentManager().popBackStack();
    }


}
