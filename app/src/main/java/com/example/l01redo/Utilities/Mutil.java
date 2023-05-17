package com.example.l01redo.Utilities;

import static android.location.LocationManager.GPS_PROVIDER;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Mutil implements LocationListener {

    private LocationManager locationManager;

    public static int MY_PERMISSIONS_REQUEST_LOCATION=1;

    private double latitude = 32.11526165;
    private double longitude = 34.817799209785015;
    //afeka cuz...idk?

    public Mutil(AppCompatActivity activity){
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION
            );
        }
        else{
            locationManager.requestLocationUpdates(GPS_PROVIDER,0,0,this);
        }
    }


    public void getUserLocation(AppCompatActivity activity){
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(GPS_PROVIDER);
            if (location != null)
                onLocationChanged(location);
        }
        else {
            Location defaultLocation = new Location(GPS_PROVIDER);
            defaultLocation.setLatitude(latitude);
            defaultLocation.setLongitude(longitude);
            onLocationChanged(defaultLocation);
        }
    }



    @Override
    public void onLocationChanged(@NonNull Location location) {

        setLatitude(location.getLatitude());
        setLongitude(location.getLongitude());
        locationManager.removeUpdates(this);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
