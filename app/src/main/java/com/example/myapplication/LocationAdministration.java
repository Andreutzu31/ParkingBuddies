package com.example.myapplication;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.NonNull;

public class LocationAdministration implements LocationListener {

    @Override
    public void onLocationChanged(@NonNull Location location) {
        System.out.println(location.getLatitude() + " " + location.getAltitude());
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}
