package com.example.didaktikapp;

import androidx.fragment.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
                   // Toast.makeText(getContext(), "Long: "+existente, Toast.LENGTH_SHORT).show();
                    if (existente == 0){
                        nuevoUsuario();
                        InicioAudioFragment fragment = new InicioAudioFragment();
                        Bundle arguments = new Bundle();
                        arguments.putString( "nombre" , etNombreInicio.getText().toString());
                        fragment.setArguments(arguments);
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment,  fragment);
                        ft.addToBackStack(null);
                        ft.commit();

                        SharedPreferences preferencias = getActivity().getSharedPreferences("datos",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencias.edit();
                        editor.putString("nombre", etNombreInicio.getText().toString());
                        editor.commit();
                    } else {
                    SharedPreferences preferencias = getActivity().getSharedPreferences("datos",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putString("nombre", etNombreInicio.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(getActivity(),MapaActivity.class);
                    startActivity(intent);
                }
                }else {
                    Toast.makeText(getContext(), "Mezedes izen bat sartu", Toast.LENGTH_SHORT).show();
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
        valores.put(DBHelper.entidadProgreso.COLUMN_NAME_PROGRESO,1);
        db.insert(DBHelper.entidadProgreso.TABLE_NAME,null, valores);

    }
}
