package com.example.didaktikapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import io.opencensus.tags.Tag;


public class PantallaCarga extends AppCompatActivity {
    ImageView progreso;
    DBHelper dbHelper;
    SQLiteDatabase dbsqlite;
    ArrayList <Lugar> lugarOnline = new ArrayList<>();
    private static ConnectivityManager manager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);
        //Comprobar si tiene internet
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        //Comprobar si tiene WIFI
        boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;

        if (isWiFi && isConnected){
            descargarBDonline();
            BorrarBDLocal();
            ActualizarBD();
        }

        Permisos();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                Intent intent = new Intent(PantallaCarga.this, InicioAventuraActivity.class);
                startActivity(intent);
            }
        }, 5000);

    }        //Aqui comprobaremos si tenemos los permisos de escritura y si no lo tenemos los pediremos
        private void Permisos () {
            int permissionCheck = ContextCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_FINE_LOCATION);
            int permisocamara = ContextCompat.checkSelfPermission(
                    this, Manifest.permission.CAMERA);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED || permisocamara != PackageManager.PERMISSION_GRANTED) {
                Log.i("Mensaje", "No se tiene permiso de ubicación!.");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA}, 225);

            } else {
                Log.i("Mensaje", "Tienes permiso para usar la ubicación.");
            }
        }

        private void descargarBDonline(){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Task<QuerySnapshot> docRef = db.collection("Lugares")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //  Log.d(Tag, document.getId() + " => " + document.getData());
                                //  String Id = document.getData();
                                long idLugar = document.getLong("Id");
                                int Id = (int) idLugar;
                                String nombre = document.getString("Nombre");
                                double Latitud = document.getDouble("Latitud");
                                double Longitud = document.getDouble("Longitud");
                                Lugar L = new Lugar(Id, nombre, Latitud, Longitud);
                                lugarOnline.add(L);
                                Toast.makeText(getApplicationContext(), "NOMBRE METER:--" + L.getNombre(), Toast.LENGTH_SHORT).show();
                            }


                        }

                    });
        }

        private void BorrarBDLocal(){
            dbHelper = new DBHelper(getApplicationContext());
            dbsqlite = dbHelper.getWritableDatabase();
            dbsqlite.execSQL("DELETE FROM "+ DBHelper.entidadLugares.TABLE_NAME);
        }

        private void ActualizarBD(){
        for (int i = 0 ; lugarOnline.size()>i;i++){
          int ID =  lugarOnline.get(i).Idlugar;
          String Nombre = lugarOnline.get(i).Nombre;
          double Latitud = lugarOnline.get(i).Latitud;
          double Longitud = lugarOnline.get(i).Longitud;
           ContentValues values = new ContentValues();
           values.put(DBHelper.entidadLugares._ID,ID);
           values.put(DBHelper.entidadLugares.COLUMN_NAME_NOMBRE,Nombre);
           values.put(DBHelper.entidadLugares.COLUMN_NAME_LATITUD, Latitud);
           values.put(DBHelper.entidadLugares.COLUMN_NAME_LONGITUD, Longitud);
           dbsqlite.insert(DBHelper.entidadLugares.TABLE_NAME, null, values);
        }
            Toast.makeText(getApplicationContext(), "ACTUALIZADA", Toast.LENGTH_SHORT).show();
        }


    }

