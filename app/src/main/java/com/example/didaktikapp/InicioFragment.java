package com.example.didaktikapp;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
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

    private Button btnEmpezar;
    private EditText etNombreInicio;
    DBHelper dbHelper;
    SQLiteDatabase db;




    private InicioViewModel mViewModel;

    public static InicioFragment newInstance() {
        return new InicioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        dbHelper = new DBHelper(getContext());
        db = dbHelper.getWritableDatabase();
        View root= inflater.inflate(R.layout.inicio_fragment, container, false);




        btnEmpezar=root.findViewById(R.id.buttonEmpezar);
        etNombreInicio=root.findViewById(R.id.editTextNombreInicio);


        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!etNombreInicio.getText().toString().equals("")){

                    ContentValues values = new ContentValues();
                    values.put(DBHelper.entidadUsuario.COLUMN_NAME_NOMBRE,etNombreInicio.getText().toString());
                    db.insert(DBHelper.entidadLugares.TABLE_NAME, null, values);

                    ContentValues valores = new ContentValues();
                    values.put(DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO,etNombreInicio.getText().toString());
                    values.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_1,false);
                    values.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_2,false);
                    values.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_3,false);
                    values.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_4,false);
                    values.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_5,false);
                    values.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_6,false);
                    db.insert(DBHelper.entidadLugares.TABLE_NAME, null, valores);

                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, new InicioAudioFragment());
                    fragmentTransaction.commit();
                }

                else {

                    System.out.println("Error");
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
