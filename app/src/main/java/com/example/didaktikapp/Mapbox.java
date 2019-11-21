package com.example.didaktikapp;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
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


public class Mapbox extends Fragment implements  PermissionsListener {

    private MapboxViewModel mViewModel;
    private boolean isEndNotified;
    private ProgressBar progressBar;
    private MapView mapView;
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


    public static Mapbox newInstance() {
        return new Mapbox();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //token de mapbox (el codigo de la cuenta)
        com.mapbox.mapboxsdk.Mapbox.getInstance(getActivity(), getString(R.string.mapbox_access_token));

        final View root= inflater.inflate(R.layout.mapbox_fragment, container, false);

        mapView = root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {



            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {

                //Marcador
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(43.209712, -2.889002))
                        .title("Arrigorriaga"));





                //el estilo que utiliza
                mapboxMap.setStyle(Style.OUTDOORS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        //Restriccion de zona del mapa
                        mapboxMap.setLatLngBoundsForCameraTarget(RESTRICTED_BOUNDS_AREA);


                        // Set up the OfflineManager
                        offlineManager = OfflineManager.getInstance(getActivity());

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
                                getActivity().getResources().getDisplayMetrics().density);

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
                                            progressBar =root.findViewById(R.id.progress_bar);
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



        });



        return root;
    }


        @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
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



    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {

    }
}
