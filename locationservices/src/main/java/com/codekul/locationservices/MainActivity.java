package com.codekul.locationservices;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_LOCATION = 1223;
    private static final int PERMISSION_LOCATION_REQUEST = 6547;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listenLocationChanges();
    }

    private void myLastLocation() {
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(this)
                        .setMessage("We need your location to proceed")
                        .setTitle("Locations")
                        .setPositiveButton("Oaky", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                        PERMISSION_LOCATION);

                            }
                        }).create().show();
            } else ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_LOCATION);
            return;
        }
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            Log.i("@codekul", "Latitude - " + location.getLatitude());
            Log.i("@codekul", "Longitude - " + location.getLongitude());
        }
    }

    private void listenLocationChanges() {
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(this)
                        .setMessage("We need your location to proceed")
                        .setTitle("Locations")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                        PERMISSION_LOCATION_REQUEST);

                            }
                        }).create().show();
            } else ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_LOCATION_REQUEST);
            return;
        }
        manager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 500, 0.5f, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("@codekul", "Latitude - " + location.getLatitude());
                Log.i("@codekul", "Longitude - " + location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });

    }

    private void showProviders(){
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> providers = manager.getAllProviders();

        for (String provider : providers) {
            Log.i("@codekul",provider);
        }
    }

    private void criteriaProvider(LocationManager manager){

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(true);
        criteria.setCostAllowed(true);
        criteria.setSpeedRequired(true);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);

        Log.i("@codekul","Best Provider - "+manager.getBestProvider(criteria,false));
    }

    private void geoCoder(){

        Log.i("@codekul","Getting data from geocoder ");
        Geocoder coder = new Geocoder(this);
        try {
            //List<Address> addresses = coder.getFromLocation(18.52,73.85,5);

            List<Address> addresses = coder.getFromLocationName("Pune, Deccan",6);
            for (Address address : addresses) {
                Log.i("@codekul","Country Code - "+address.getCountryCode());
                Log.i("@codekul","Admin Area - "+address.getAdminArea());
                Log.i("@codekul","Country Name - "+address.getCountryName());
                Log.i("@codekul","Postal Code - "+address.getPostalCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_LOCATION){
            if(grantResults.length > 0){
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    myLastLocation();
                }
            }
        }
        if(requestCode == PERMISSION_LOCATION_REQUEST){
            if(grantResults.length > 0){
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    listenLocationChanges();
                }
            }
        }
    }
}
