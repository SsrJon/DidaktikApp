package com.example.didaktikapp;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.ContentValues;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.app.Application;
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
import android.widget.Toast;

import static com.mapbox.mapboxsdk.Mapbox.getAccessToken;
import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

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
                    long existente = DatabaseUtils.queryNumEntries(db, DBHelper.entidadUsuario.TABLE_NAME,
                            DBHelper.entidadUsuario.COLUMN_NAME_NOMBRE + "=? ", new String[] {etNombreInicio.getText().toString()});
                    if (existente == 0){
                        nuevoUsuario();
                    }
                    InicioFragment f = new InicioFragment();
                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                    Bundle args = new Bundle();
                    args.putString("nombre", etNombreInicio.getText().toString());
                    f.setArguments(args);
                    fragmentTransaction.replace(R.id.fragment, new InicioAudioFragment());
                    fragmentTransaction.commit();
                }

                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.izena), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;

    }


    public void onBackPressed(){
        //Bloquea el boton hacia atras
        getActivity().getSupportFragmentManager().popBackStack();
    }
    public void nuevoUsuario(){
        ContentValues values = new ContentValues();
        values.put(DBHelper.entidadUsuario.COLUMN_NAME_NOMBRE,etNombreInicio.getText().toString());
        db.insert(DBHelper.entidadUsuario.TABLE_NAME, null, values);
        ContentValues valores = new ContentValues();
        valores.put(DBHelper.entidadProgreso.COLUMN_NAME_ID_USUARIO,etNombreInicio.getText().toString());
        valores.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_1,0);
        valores.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_2,0);
        valores.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_3,0);
        valores.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_4,0);
        valores.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_5,0);
        valores.put(DBHelper.entidadProgreso.COLUMN_NAME_PTO_6,0);
        db.insert(DBHelper.entidadProgreso.TABLE_NAME,null, valores);

    }
}
