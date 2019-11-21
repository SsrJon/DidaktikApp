package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ClipData;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.didaktikapp.R.drawable.fondo2;


public class MainActivity extends AppCompatActivity implements Mapa.OnFragmentInteractionListener {
    GoogleMap mapa;
    MapView mapaView;
    int pantalla = 0;
    ConstraintLayout cons;
    FloatingActionButton play;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        play = findViewById(R.id.FAprinicipal);
        cons = findViewById(R.id.cons);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new Mapa();
        fragmentTransaction.replace(R.id.manolo, fragment, null).commit();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "hola");
                Gurutzegrama frag_Prod = new Gurutzegrama();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.manolo, frag_Prod);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    //Permiso deneado
                }
                return;
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.overflow,menu);
    return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if(id == R.id.juegos){
            if (pantalla==0){
                setTheme(R.style.AppTheme2);
                setContentView(R.layout.activity_main);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment = new Mapa();
                fragmentTransaction.replace(R.id.manolo, fragment, null).commit();
                pantalla=1;
            }else if (pantalla == 1){
                setTheme(R.style.AppTheme);
                setContentView(R.layout.activity_main);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment = new Mapa();
                fragmentTransaction.replace(R.id.manolo, fragment, null).commit();
                pantalla=0;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}


