package com.example.l01redo.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.l01redo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment supportMapFragment;
    GoogleMap googleMap;

    public MapFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);

        findViews();
        startFragment();

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
               clickOnMap();
    }

    public void findViews(){
        supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);
    }

    private void startFragment(){
        supportMapFragment.getMapAsync(this);
    }


    public void clickOnMap(){
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                googleMap.clear();
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        latLng,10
                ));
                googleMap.addMarker(markerOptions);

            }
        });

    }

    public void showLocation(double latitude, double longitude){
        if(googleMap != null){
            MarkerOptions markerOptions = new MarkerOptions();
            LatLng dest = new LatLng(latitude,longitude);
            markerOptions.position(dest);
            markerOptions.title(dest.latitude + " : " + dest.longitude);
            googleMap.clear(); // what
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    dest,16f
            ));
            googleMap.addMarker(markerOptions);
        }
    }


}
