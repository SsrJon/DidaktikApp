package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

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

public abstract class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mapa;
    MapView mapaView;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        mapaView = findViewById(R.id.mapView);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }


        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {

            //La posición del mapa y el nombre del marcador
            LatLng position = new LatLng(43.257385, -2.933527);
            String markerText = "Goazen";

            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.i("DEBUG", "onMapReady");

                //Añade el marcador
                Marker marker = googleMap.addMarker(new MarkerOptions().position(position).title(markerText));

                //Zoom del mapa
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(position, 19);
                googleMap.animateCamera(cameraUpdate);

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



    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }



}


