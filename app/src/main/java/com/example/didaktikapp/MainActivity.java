package com.example.didaktikapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.OnCameraTrackingChangedListener;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.offline.OfflineRegion;
import com.mapbox.mapboxsdk.offline.OfflineRegionError;
import com.mapbox.mapboxsdk.offline.OfflineRegionStatus;
import com.mapbox.mapboxsdk.offline.OfflineTilePyramidRegionDefinition;

import org.json.JSONObject;

import java.util.List;

import timber.log.Timber;

import static com.example.didaktikapp.R.drawable.fondo2;


public class MainActivity extends AppCompatActivity implements
        com.mapbox.mapboxsdk.maps.OnMapReadyCallback, /*OnLocationClickListener,*/ PermissionsListener, OnCameraTrackingChangedListener {

    private PermissionsManager permissionsManager;
    private com.mapbox.mapboxsdk.maps.MapView mapView;
    private MapboxMap mapboxMap;
    private LocationComponent locationComponent;
    private boolean isInTrackingMode;
    int pantalla =0;
    FloatingActionButton BTNjuegos;



    private boolean isEndNotified;
    private ProgressBar progressBar;

    private OfflineManager offlineManager;


    // JSON encoding/decoding
    public static final String JSON_CHARSET = "UTF-8";
    public static final String JSON_FIELD_REGION_NAME = "FIELD_REGION_NAME";

    //Zona accesible en el mapa
    private static final LatLng BOUND_CORNER_NW = new LatLng(43.202712, -2.90102);
    private static final LatLng BOUND_CORNER_SE = new LatLng(43.221812, -2.88002);
    private static final LatLngBounds RESTRICTED_BOUNDS_AREA = new LatLngBounds.Builder()
            .include(BOUND_CORNER_NW)
            .include(BOUND_CORNER_SE)
            .build();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// Mapbox access token is configured here. This needs to be called either in your application
// object or in the same activity which contains the mapview.
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

// This contains the MapView in XML and needs to be called after the access token is configured.
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        BTNjuegos = findViewById(R.id.btnJuegos);
        BTNjuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),seleccionJuego.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        mapboxMap.setStyle(Style.OUTDOORS, new Style.OnStyleLoaded() {


            @Override
            public void onStyleLoaded(@NonNull Style style) {
                enableLocationComponent(style);

                //Restriccion de zona del mapa
                    mapboxMap.setLatLngBoundsForCameraTarget(RESTRICTED_BOUNDS_AREA);


                    // Set up the OfflineManager
                    offlineManager = OfflineManager.getInstance(MainActivity.this);

                    // la zona del mapa que se va a descargar (bounding box)
                    LatLngBounds latLngBounds = new LatLngBounds.Builder()
                            .include(new LatLng(43.201712, -2.901002)) // Northeast
                            .include(new LatLng(43.223712, -2.889002)) // Southwest
                            .build();

                    // Define la region  offline
                    OfflineTilePyramidRegionDefinition definition = new OfflineTilePyramidRegionDefinition(
                            style.getUri(),
                            latLngBounds,
                            10,
                            20,
                            MainActivity.this.getResources().getDisplayMetrics().density);

                    // Set the metadata
                    byte[] metadata;
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put(JSON_FIELD_REGION_NAME, "Arrigorriaga");
                        String json = jsonObject.toString();
                        metadata = json.getBytes(JSON_CHARSET);
                    } catch (Exception exception) {
                        Timber.e("Failed to encode metadata: %s", exception.getMessage());
                        metadata = null;
                    }

                    // Crea la region
                    if (metadata != null) {
                        offlineManager.createOfflineRegion(
                                definition,
                                metadata,
                                new OfflineManager.CreateOfflineRegionCallback() {
                                    @Override
                                    public void onCreate(OfflineRegion offlineRegion) {
                                        offlineRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);

                                        // muestra la barra de progreso
                                        progressBar =findViewById(R.id.progress_bar);
                                        startProgress();

                                        // Monitoriza la descarga usando setObserver
                                        offlineRegion.setObserver(new OfflineRegion.OfflineRegionObserver() {
                                            @Override
                                            public void onStatusChanged(OfflineRegionStatus status) {

                                                // Calculate the download percentage and update the progress bar
                                                double percentage = status.getRequiredResourceCount() >= 0
                                                        ? (100.0 * status.getCompletedResourceCount() / status.getRequiredResourceCount()) :
                                                        0.0;

                                                if (status.isComplete()) {
                                                    // Download complete
                                                    endProgress(getString(R.string.simple_offline_end_progress_success));
                                                } else if (status.isRequiredResourceCountPrecise()) {
                                                    // Switch to determinate state
                                                    setPercentage((int) Math.round(percentage));
                                                }
                                            }

                                            @Override
                                            public void onError(OfflineRegionError error) {
                                                // If an error occurs, print to logcat
                                                Timber.e("onError reason: %s", error.getReason());
                                                Timber.e("onError message: %s", error.getMessage());
                                            }

                                            @Override
                                            public void mapboxTileCountLimitExceeded(long limit) {
                                                // Notify if offline region exceeds maximum tile count
                                                Timber.e("Mapbox tile count limit exceeded: %s", limit);
                                            }
                                        });
                                    }

                                    @Override
                                    public void onError(String error) {
                                        Timber.e("Error: %s", error);
                                    }
                                });
                    }







            }
        });
    }



    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
// Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

// Create and customize the LocationComponent's options
            LocationComponentOptions customLocationComponentOptions = LocationComponentOptions.builder(this)
                    .elevation(5)
                    .accuracyAlpha(.6f)
                    .accuracyColor(Color.CYAN)
                    .foregroundDrawable(R.drawable.uva_gps)
                    .build();

// Get an instance of the component
            locationComponent = mapboxMap.getLocationComponent();

            LocationComponentActivationOptions locationComponentActivationOptions =
                    LocationComponentActivationOptions.builder(this, loadedMapStyle)
                            .locationComponentOptions(customLocationComponentOptions)
                            .build();

// Activate with options
            locationComponent.activateLocationComponent(locationComponentActivationOptions);

// Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

// Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

// Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);

/*// Add the location icon click listener
            locationComponent.addOnLocationClickListener(l);*/

// Add the camera tracking listener. Fires if the map camera is manually moved.
            locationComponent.addOnCameraTrackingChangedListener(this);

            findViewById(R.id.FAprinicipal).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isInTrackingMode) {
                        isInTrackingMode = true;
                        locationComponent.setCameraMode(CameraMode.TRACKING);
                        locationComponent.zoomWhileTracking(16f);
                        //Toast.makeText(LocationComponentOptionsActivity.this, getString(R.string.tracking_enabled),
                        //Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(LocationComponentOptionsActivity.this, getString(R.string.tracking_already_enabled),
                        // Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @SuppressWarnings( {"MissingPermission"})
    // @Override
   /* public void onLocationComponentClick() {
        if (locationComponent.getLastKnownLocation() != null) {
            Toast.makeText(this, String.format(getString(R.string.),
                    locationComponent.getLastKnownLocation().getLatitude(),
                    locationComponent.getLastKnownLocation().getLongitude()), Toast.LENGTH_LONG).show();
        }
    }*/

    @Override
    public void onCameraTrackingDismissed() {
        isInTrackingMode = false;
    }

    @Override
    public void onCameraTrackingChanged(int currentMode) {
// Empty on purpose
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, "JUANJO", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            Toast.makeText(this, "MANUEL", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @SuppressWarnings( {"MissingPermission"})
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }



    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        if (offlineManager != null) {
            offlineManager.listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() {
                @Override
                public void onList(OfflineRegion[] offlineRegions) {
                    if (offlineRegions.length > 0) {
                        // delete the last item in the offlineRegions list which will be yosemite offline map
                        offlineRegions[(offlineRegions.length - 1)].delete(new OfflineRegion.OfflineRegionDeleteCallback() {
                            @Override
                            public void onDelete() {
                                //Toast.makeText(getActivity(), getString(R.string.basic_offline_deleted_toast), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(String error) {
                                Timber.e("On delete error: %s", error);
                            }
                        });
                    }
                }

                @Override
                public void onError(String error) {
                    Timber.e("onListError: %s", error);
                }
            });
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    // Progress bar methods
    private void startProgress() {

        // Start and show the progress bar
        isEndNotified = false;
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void setPercentage(final int percentage) {
        progressBar.setIndeterminate(false);
        progressBar.setProgress(percentage);
    }

    private void endProgress(final String message) {
        // Don't notify more than once
        if (isEndNotified) {
            return;
        }

        // Stop and hide the progress bar
        isEndNotified = true;
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);


    }





    public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.overflow,menu);
    return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();
        if(id == R.id.juegos){
            if (pantalla==0){

                pantalla=1;
            }else if (pantalla == 1){

                pantalla=0;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}


