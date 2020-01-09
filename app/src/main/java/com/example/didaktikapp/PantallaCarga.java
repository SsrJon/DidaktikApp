package com.example.didaktikapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import io.opencensus.tags.Tag;


public class PantallaCarga extends AppCompatActivity {
    ImageView progreso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);
        Permisos();
           Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // acciones que se ejecutan tras los milisegundos
                    Intent intent = new Intent(PantallaCarga.this,InicioAventuraActivity.class);
                    startActivity(intent);
                }
            }, 5000);
    }
    // Write a message to the database
    //FirebaseFirestore db = FirebaseFirestore.getInstance();
   /* DocumentReference docRef = db.collection("Lugares").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()){
                   document.get("Nombre"), (String) document.get("Latitud"),(String) document.get("Longitud");
                }
            }else{
            }
        }
    });*/


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Task<QuerySnapshot> docRef = db.collection("Lugares")
            .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                  //  Log.d(Tag, document.getId() + " => " + document.getData());
                    

                }
            } else {
               // Log.w(TAG, "Error getting documents.", task.getException());
            }
        }
    });

    //Aqui comprobaremos si tenemos los permisos de escritura y si no lo tenemos los pediremos
    private void Permisos(){
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permisocamara = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED || permisocamara != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso de ubicación!.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CAMERA}, 225);

        } else {
            Log.i("Mensaje", "Tienes permiso para usar la ubicación.");
        }
    }

}