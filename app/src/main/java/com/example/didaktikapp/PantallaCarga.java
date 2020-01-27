package com.example.didaktikapp;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.didaktikapp.sopadeletras.data.sqlite.DbHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class PantallaCarga extends AppCompatActivity {
    ImageView progreso;
    DBHelper dbHelper;
    SQLiteDatabase dbsqlite;
    ArrayList <Lugar> lugarOnline = new ArrayList<>();
    ArrayList <Lugar> lugarOffline = new ArrayList<>();
    private static ConnectivityManager manager;
    boolean Actualizada = false;
    boolean isWiFi;
    boolean descargada = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);
        //Comprobar si tiene internet
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        //Comprobar si tiene WIFI
        if(isConnected){
             isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        }else{
             isWiFi = false;
        }

        if (isWiFi && isConnected){
            descargarBDonline();
            entrarComprobacion();

        }else {
            Actualizada= true;
        }

        Permisos();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (Actualizada) {
                    // acciones que se ejecutan tras los milisegundos
                    Intent intent = new Intent(PantallaCarga.this, InicioAventuraActivity.class);
                    startActivity(intent);
                }
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
                            }
                            descargada = true;
                            Toast.makeText(getApplicationContext(),"Lugaresct online"+lugarOnline.size(),Toast.LENGTH_LONG).show();

                        }

                    });
        }

        private void DescargarBDLocal(){
        Toast.makeText(getApplicationContext(),"Entro en descargar BD local",Toast.LENGTH_LONG).show();
            dbHelper = new DBHelper(getApplicationContext());
            dbsqlite = dbHelper.getWritableDatabase();
            Cursor cursorcantidad = dbsqlite.query(DBHelper.entidadLugares.TABLE_NAME,null,null,null,null,null,null);
            while (cursorcantidad.moveToNext()){
                int Id = cursorcantidad.getInt(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadLugares._ID));
                String nom = cursorcantidad.getString(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadLugares.COLUMN_NAME_NOMBRE));
                double Latitud = cursorcantidad.getDouble(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadLugares.COLUMN_NAME_LATITUD));
                double Longitud = cursorcantidad.getDouble(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadLugares.COLUMN_NAME_LONGITUD));
                Lugar L = new Lugar(Id,nom,Latitud,Longitud);
                lugarOffline.add(L);
            }
            Toast.makeText(getApplicationContext(),"Lugarres local"+lugarOffline.size(),Toast.LENGTH_LONG).show();

        }

        private void ActualizarBD(){
           /* Toast.makeText(getApplicationContext(),"Entro en Actualizar BD",Toast.LENGTH_LONG).show();


            HashMap<Integer, Integer> comparacion = new HashMap<>();
            int i=0;
            while(lugarOffline.size()>i){
                Toast.makeText(getApplicationContext(),"Entro a crear hasmap",Toast.LENGTH_LONG).show();
                comparacion.put(lugarOffline.get(i).getIdlugar(), 1);
                i = i+1;
            }*/
            /*comparacion.put(lugarOffline.get(0).getIdlugar(), 1);
            comparacion.put(lugarOffline.get(1).getIdlugar(), 1);
            comparacion.put(lugarOffline.get(2).getIdlugar(), 1);
            comparacion.put(lugarOffline.get(3).getIdlugar(), 1);
            comparacion.put(lugarOffline.get(4).getIdlugar(), 1);
            comparacion.put(lugarOffline.get(5).getIdlugar(), 1);*/

            for(int j=0;lugarOnline.size()>j;j++){
               boolean Encontrado = false;
                for(int k = 0;lugarOffline.size()>k&&!Encontrado;k++){
                    if(lugarOnline.get(j).getIdlugar()==lugarOffline.get(k).getIdlugar()){
                        lugarOffline.get(k).setNombre(lugarOnline.get(j).getNombre());
                        lugarOffline.get(k).setLatitud(lugarOnline.get(j).getLatitud());
                        lugarOffline.get(k).setLongitud(lugarOnline.get(j).getLongitud());
                        Encontrado = true;
                    }
                }
                if (!Encontrado){
                    lugarOffline.add(lugarOnline.get(j));
                    ContentValues values = new ContentValues();
                    values.put(DBHelper.entidadLugares.COLUMN_NAME_NOMBRE,lugarOnline.get(j).Nombre);
                    values.put(DBHelper.entidadLugares._ID,lugarOnline.get(j).Idlugar);
                    values.put(DBHelper.entidadLugares.COLUMN_NAME_LATITUD,lugarOnline.get(j).Latitud);
                    values.put(DBHelper.entidadLugares.COLUMN_NAME_LONGITUD,lugarOnline.get(j).Longitud);
                    dbsqlite.insert(DBHelper.entidadLugares.TABLE_NAME, null, values);
                }
            }
            boolean Encontrado;
            for(int j=0;lugarOffline.size()>j;j++){
                Log.d("david", "compruebo: "+lugarOffline.get(j).Nombre);
                //Toast.makeText(getApplicationContext(),"compruebo: "+lugarOffline.get(j).Nombre,Toast.LENGTH_LONG).show();
                Encontrado = false;
                for(int k = 0;lugarOnline.size()>k&&!Encontrado;k++){
                    if(lugarOffline.get(j).getIdlugar()==lugarOnline.get(k).getIdlugar()){
                        Log.d("david", lugarOffline.get(j).Nombre);
                        //Toast.makeText(getApplicationContext(),"Ecuentro: "+lugarOffline.get(j).Nombre,Toast.LENGTH_LONG).show();
                        Encontrado = true;
                    }
                }
                Toast.makeText(getApplicationContext(),"miro if borrar: "+lugarOffline.get(j).Nombre,Toast.LENGTH_LONG).show();
                if (!Encontrado){
                    Log.d("david", "Entro a borrar: " + lugarOffline.get(j).Nombre);
                    //Toast.makeText(getApplicationContext(),"Entro a borrar: " + lugarOffline.get(j).Nombre,Toast.LENGTH_LONG).show();
                    dbsqlite.delete(DBHelper.entidadLugares.TABLE_NAME, DBHelper.entidadLugares._ID + "=" + lugarOffline.get(j).Idlugar, null);
                    }
            }


          /*  Iterator<Map.Entry<Integer, Integer>> it = comparacion.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<Integer, Integer> e = it.next();

                Toast.makeText(getApplicationContext(),"Entro a borrar",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Entro a borrar : "+lugarOffline.get(e.getKey()-1),Toast.LENGTH_LONG).show();

                if(e.getValue()==0){
                    Toast.makeText(getApplicationContext(),"Entro a borrar",Toast.LENGTH_LONG).show();
                    dbsqlite.delete(DBHelper.entidadLugares.TABLE_NAME, DBHelper.entidadLugares._ID + "=" + lugarOffline.get(e.getKey()-1).Idlugar, null);
                    lugarOffline.remove(lugarOffline.get(e.getKey()-1));
                }

            }*/


            Actualizada = true;
        }

        public void comprobacion(){
            Toast.makeText(getApplicationContext(),"Lugaresctualizados entro en comprobacion ",Toast.LENGTH_LONG).show();

            if (descargada){
                Toast.makeText(getApplicationContext(),"Lugaresctualizados entro el IF en comprobacion ",Toast.LENGTH_LONG).show();
                DescargarBDLocal();
                ActualizarBD();
            }else{
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                       comprobacion();
                   }
                }, 500);
            }
        }

        public void entrarComprobacion(){
            if (descargada){
                comprobacion();
            }else{
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        comprobacion();
                    }
                }, 500);
            }
        }

    }

