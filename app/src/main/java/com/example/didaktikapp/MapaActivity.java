package com.example.didaktikapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.OnCameraTrackingChangedListener;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.offline.OfflineRegion;
import com.mapbox.mapboxsdk.offline.OfflineRegionError;
import com.mapbox.mapboxsdk.offline.OfflineRegionStatus;
import com.mapbox.mapboxsdk.offline.OfflineTilePyramidRegionDefinition;

import org.json.JSONObject;

import java.util.List;

import timber.log.Timber;

import static com.mapbox.turf.TurfConstants.UNIT_KILOMETERS;

import com.mapbox.turf.TurfMeasurement;


public class MapaActivity extends AppCompatActivity implements
        OnMapReadyCallback, PermissionsListener, OnCameraTrackingChangedListener {
    private PermissionsManager permissionsManager;
    private MapView mapView;
    private MapboxMap mapboxMap;
    private LocationComponent locationComponent;
    private boolean isInTrackingMode;
    int pantalla = 0;

    private FloatingActionButton juegos;
    private boolean isEndNotified;
    private ProgressBar progressBar;
    private OfflineManager offlineManager;


    // JSON encoding/decoding
    public static final String JSON_CHARSET = "UTF-8";
    public static final String JSON_FIELD_REGION_NAME = "FIELD_REGION_NAME";

    //Zona accesible en el mapa
    //private static final LatLng BOUND_CORNER_NW = new LatLng(43.202712, -2.91002);
    // private static final LatLng BOUND_CORNER_SE = new LatLng(43.221812, -2.88002);
    /*private static final LatLngBounds RESTRICTED_BOUNDS_AREA = new LatLngBounds.Builder()
            .include(BOUND_CORNER_NW)
            .include(BOUND_CORNER_SE)
            .build();*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Mapbox access token is configured here. This needs to be called either in your application
        // object or in the same activity which contains the mapview.
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        // This contains the MapView in XML and needs to be called after the access token is configured.
        setContentView(R.layout.activity_mapa);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        juegos = findViewById(R.id.btnJuegos);
        juegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapaActivity.this, seleccionJuego.class);
                startActivity(intent);

            }

        });
    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        //Punto 1  Larrea eskultura
        final MarkerOptions punto1 = new MarkerOptions();
        punto1.title("Larrea eskultura");
        IconFactory iconFactoryPunto1 = IconFactory.getInstance(MapaActivity.this);
        Icon iconPunto1 = iconFactoryPunto1.fromResource(R.drawable.marcador1);
        punto1.icon(iconPunto1);
        punto1.position(new LatLng(43.211583, -2.886917));
        mapboxMap.addMarker(punto1);


       /*//Punto 2  Arrigorriagako Udaletxea
        MarkerOptions punto2 =new MarkerOptions();
        punto2.title("Arrigorriagako Udaletxea");
        IconFactory iconFactoryPunto2= IconFactory.getInstance(MapaActivity.this);
        Icon iconPunto2= iconFactoryPunto2.fromResource(R.drawable.marcador2);
        punto2.icon(iconPunto2);
        punto2.position(new LatLng(43.205978,-2.887869));
        mapboxMap.addMarker(punto2);

        //Punto 3 Andra Maria Magdalena eliza
        MarkerOptions punto3 =new MarkerOptions();
        punto3.title("Maria Magdalena eliza");
        IconFactory iconFactoryPunto3= IconFactory.getInstance(MapaActivity.this);
        Icon iconPunto3= iconFactoryPunto3.fromResource(R.drawable.marcador3);
        punto3.icon(iconPunto3);
        punto3.position(new LatLng(43.205548,-2.888705));
        mapboxMap.addMarker(punto3);

        //Punto 4 Hiltegi Zaharra
        MarkerOptions punto4 =new MarkerOptions();
        punto4.title("Hiltegi Zaharra");
        IconFactory iconFactoryPunto4= IconFactory.getInstance(MapaActivity.this);
        Icon iconPunto4= iconFactoryPunto4.fromResource(R.drawable.marcador4);
        punto4.icon(iconPunto4);
        punto4.position(new LatLng(43.204889,-2.887833));
        mapboxMap.addMarker(punto4);

        //Punto 5 Landaederreagako Santo Kristo baseliza
        MarkerOptions punto5 =new MarkerOptions();
        punto5.title("Landaederreagako Santo Kristo baseliza");
        IconFactory iconFactoryPunto5= IconFactory.getInstance(MapaActivity.this);
        Icon iconPunto5= iconFactoryPunto5.fromResource(R.drawable.marcador5);
        punto5.icon(iconPunto5);
        punto5.position(new LatLng(43.209306,-2.893722));
        mapboxMap.addMarker(punto5);

        //Punto 6 Abrisketako San Pedro baseleizea
        MarkerOptions punto6 =new MarkerOptions();
        punto6.title("Abrisketako San Pedro baseleizea");
        IconFactory iconFactoryPunto6= IconFactory.getInstance(MapaActivity.this);
        Icon iconPunto6= iconFactoryPunto6.fromResource(R.drawable.marcador6);
        punto6.icon(iconPunto6);
        punto6.position(new LatLng(43.210500,-2.909417));
        mapboxMap.addMarker(punto6);*/

        mapboxMap.setStyle(Style.OUTDOORS, new Style.OnStyleLoaded() {

            @Override
            public void onStyleLoaded(@NonNull Style style) {
                enableLocationComponent(style);


                //Restriccion de zona del mapa
                // mapboxMap.setLatLngBoundsForCameraTarget(RESTRICTED_BOUNDS_AREA);


                // Set up the OfflineManager
                offlineManager = OfflineManager.getInstance(MapaActivity.this);

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
                        MapaActivity.this.getResources().getDisplayMetrics().density);

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
                                public void onCreate(final OfflineRegion offlineRegion) {
                                    offlineRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);

                                    // muestra la barra de progreso
                                    progressBar = findViewById(R.id.progress_bar);
                                    startProgress();

                                    // Monitoriza la descarga usando setObserver
                                    offlineRegion.setObserver(new OfflineRegion.OfflineRegionObserver() {
                                        @SuppressLint("NewApi")
                                        @Override
                                        public void onStatusChanged(OfflineRegionStatus status) {
                                            // Location localizacion = mapboxMap.getLocationComponent().getLastKnownLocation();

                                            Location localizacion = mapboxMap.getLocationComponent().getLastKnownLocation();
                                            double distancia = TurfMeasurement.distance(Point.fromLngLat(localizacion.getLongitude(),localizacion.getLatitude()),Point.fromLngLat(punto1.getPosition().getLongitude(),punto1.getPosition().getLatitude()));
                                            System.out.println(distancia);

                                            if (distancia *1000 <= 9){
                                                System.out.println("llegue");
                                                //Intent intent = new Intent(MapaActivity.this, GurutzegramaActivity.class);
                                                //startActivity(intent);
                                            }


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

    //---------
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
   /*public void onLocationComponentClick() {
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
        Toast.makeText(this, "MANUEL", Toast.LENGTH_LONG).show();
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

    //-----

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
                Log.d("tag", "juegos");
                Intent intent = new Intent(MapaActivity.this,GurutzegramaActivity.class);
                startActivity(intent);

                pantalla=1;
            }else if (pantalla == 1){

                pantalla=0;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed () {

        //Bloquea el boton hacia atras

    }


}






