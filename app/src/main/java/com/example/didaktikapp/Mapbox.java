package com.example.didaktikapp;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
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
import com.mapbox.mapboxsdk.plugins.markerview.MarkerView;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerViewManager;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

public class Mapbox extends Fragment implements OnMapReadyCallback , PermissionsListener {

    private MapboxViewModel mViewModel;
    private boolean isEndNotified;
    private ProgressBar progressBar;
    private MapView mapView;
    private OfflineManager offlineManager;

    // JSON encoding/decoding
    public static final String JSON_CHARSET = "UTF-8";
    public static final String JSON_FIELD_REGION_NAME = "FIELD_REGION_NAME";

    private static final String SOURCE_ID = "SOURCE_ID";
    private static final String ICON_ID = "ICON_ID";
    private static final String LAYER_ID = "LAYER_ID";




    public static Mapbox newInstance() {
        return new Mapbox();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Mapbox access token is configured here. This needs to be called either in your application
        // object or in the same activity which contains the mapview.
        com.mapbox.mapboxsdk.Mapbox.getInstance(getActivity(), getString(R.string.mapbox_access_token));

        final View root= inflater.inflate(R.layout.mapbox_fragment, container, false);

        mapView = root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {

                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(43.209712, -2.889002))
                        .title("Arrigorriaga"));









                mapboxMap.setStyle(Style.OUTDOORS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Set up the OfflineManager
                        offlineManager = OfflineManager.getInstance(getActivity());

                        // Create a bounding box for the offline region
                        LatLngBounds latLngBounds = new LatLngBounds.Builder()
                                .include(new LatLng(43.209712, -2.889002)) // Northeast
                                .include(new LatLng(43.109712, -2.989002)) // Southwest
                                .build();

                        // Define the offline region
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
                            jsonObject.put(JSON_FIELD_REGION_NAME, "Yosemite National Park");
                            String json = jsonObject.toString();
                            metadata = json.getBytes(JSON_CHARSET);
                        } catch (Exception exception) {
                            Timber.e("Failed to encode metadata: %s", exception.getMessage());
                            metadata = null;
                        }

                        // Create the region asynchronously
                        if (metadata != null) {
                            offlineManager.createOfflineRegion(
                                    definition,
                                    metadata,
                                    new OfflineManager.CreateOfflineRegionCallback() {
                                        @Override
                                        public void onCreate(OfflineRegion offlineRegion) {
                                            offlineRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);

                                            // Display the download progress bar
                                            progressBar =root.findViewById(R.id.progress_bar);
                                            startProgress();

                                            // Monitor the download progress using setObserver
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
                                Toast.makeText(
                                        getActivity(),
                                        getString(R.string.basic_offline_deleted_toast),
                                        Toast.LENGTH_LONG
                                ).show();
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

        // Show a toast
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {





    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {

    }
}
