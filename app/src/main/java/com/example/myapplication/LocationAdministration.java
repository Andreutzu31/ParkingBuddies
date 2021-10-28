package com.example.myapplication;

import static com.example.myapplication.MapsActivity.getAppContext;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.location.provider.ProviderProperties;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;

public class LocationAdministration implements LocationListener {

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.d("Update", "New Location: " + location.getLatitude() + " " + location.getAltitude());
        System.out.println("New Location: " + location.getLatitude() + " " + location.getAltitude());
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Log.d("Update", "New provider: " + provider);

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Log.d("Update", "New Location: " +provider);

    }

    public static void setMock(double latitude, double longitude, float accuracy) {
        LocationManager locMgr = (LocationManager) getAppContext().getSystemService(Context.LOCATION_SERVICE);
        locMgr.removeTestProvider(LocationManager.GPS_PROVIDER);
        locMgr.addTestProvider (LocationManager.GPS_PROVIDER,
                "requiresNetwork" == "",
                "requiresSatellite" == "",
                "requiresCell" == "",
                "hasMonetaryCost" == "",
                "supportsAltitude" == "",
                "supportsSpeed" == "",
                "supportsBearing" == "",
                ProviderProperties.POWER_USAGE_LOW,
                ProviderProperties.ACCURACY_FINE);

        Location newLocation = new Location(LocationManager.GPS_PROVIDER);

        newLocation.setLatitude(latitude);
        newLocation.setLongitude(longitude);
        newLocation.setAccuracy(accuracy);
        newLocation.setAltitude(0);
        newLocation.setTime(System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            newLocation.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        }
        locMgr.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);
        locMgr.setTestProviderStatus(LocationManager.GPS_PROVIDER,
                LocationProvider.AVAILABLE,
                null,System.currentTimeMillis());

        locMgr.setTestProviderLocation(LocationManager.GPS_PROVIDER, newLocation);}

}
