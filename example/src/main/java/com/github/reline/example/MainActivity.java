package com.github.reline.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.github.reline.GoogleMapsBottomSheetBehavior;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, OnStreetViewPanoramaReadyCallback {

    private GoogleMap map;
    private GoogleMapsBottomSheetBehavior behavior;
    private View parallax;
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        SupportStreetViewPanoramaFragment streetViewPanoramaFragment = (SupportStreetViewPanoramaFragment) getSupportFragmentManager()
                .findFragmentById(R.id.parallax);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

        final View bottomsheet = findViewById(R.id.bottomsheet);
        behavior = GoogleMapsBottomSheetBehavior.from(bottomsheet);
        parallax = findViewById(R.id.parallax);
        behavior.setParallax(parallax);
        behavior.anchorView(fab);

        // wait for the bottomsheet to be laid out
        bottomsheet.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // set the height of the parallax to fill the gap between the anchor and the top of the screen
                CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(parallax.getMeasuredWidth(), behavior.getAnchorOffset() / 2);
                parallax.setLayoutParams(layoutParams);
                bottomsheet.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        behavior.setBottomSheetCallback(new GoogleMapsBottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, @GoogleMapsBottomSheetBehavior.State int newState) {
                // each time the bottomsheet changes position, animate the camera to keep the pin in view
                // normally this would be a little more complex (getting the pin location and such),
                // but for the purpose of an example this is enough to show how to stay centered on a pin
                map.animateCamera(CameraUpdateFactory.newLatLng(SYDNEY));
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        behavior.anchorMap(map);
        // Add a marker in Sydney and move the camera
        map.addMarker(new MarkerOptions().position(SYDNEY).title("Marker in Sydney"));
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                behavior.setState(GoogleMapsBottomSheetBehavior.STATE_COLLAPSED);
                behavior.setHideable(false);
                map.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                return true;
            }
        });
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                behavior.setHideable(true);
                behavior.setState(GoogleMapsBottomSheetBehavior.STATE_HIDDEN);
            }
        });
        map.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama.setPosition(SYDNEY);
        streetViewPanorama.setUserNavigationEnabled(false);
    }
}
