package com.codekul.mylocationservices;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_LOCATION = 1234;

    private LocationManager manager;
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        geocoder = new Geocoder(this);

        performClick(R.id.btnProviders, R.id.btnMakeProvider, R.id.btnGeoCoder);

        registerListener();
    }

    private void registerListener(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(this)
                        .setMessage("We need to access your GPS for getting location")
                        .setPositiveButton("Okay", (d, which) -> {

                            ActivityCompat.requestPermissions(this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                                    PERMISSION_LOCATION);
                        })
                        .setNegativeButton("Exit",(d, which) ->{
                            d.dismiss();
                        }).show();

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSION_LOCATION);
            }

            return;
        }
        manager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 1000, 0.5f, listener);
    }

    private void performClick(int ... ids) {
        for (int i = 0; i < ids.length; i++)
            findViewById(ids[i]).setOnClickListener(this::clicked);
    }
    private void clicked(View view) {
        if(view.getId() == R.id.btnProviders) providers();
        else if(view.getId() == R.id.btnMakeProvider) makeProvider();
        else if(view.getId() == R.id.btnGeoCoder) geoCoder();
    }

    private void makeProvider() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(true);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        String provider = manager.getBestProvider(criteria,false);
        Log.i("@codekul","Created Provider - "+provider);
    }

    private void providers(){
        List<String> providers =  manager.getAllProviders();

        for (String provider : providers) {
            Log.i("@codekul","Provider is - "+provider);
        }
    }

    private void geoCoder(){

        try {
            //List<Address> addresses = geocoder.getFromLocation(18.72,72.79, 5);
            List<Address> addresses = geocoder.getFromLocationName("CodeKul Pune", 5);
            for (Address address : addresses) {
                l("Country Name - "+ address.getCountryName());
                l("Admin Area - "+address.getAdminArea());
                l("Premises - "+address.getPremises());
                l("Address - "+address.getAddressLine(0));
                l("Phone - "+address.getPhone());
                l("Lat - "+address.getLatitude());
                l("Lng - "+address.getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void l(String msg){
        Log.i("@codkul",msg);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_LOCATION){
            if(grantResults.length > 0
                    && grantResults[0]  == PackageManager.PERMISSION_GRANTED
                    && grantResults[1]  == PackageManager.PERMISSION_GRANTED) {
                registerListener();
            }
        }
    }

    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            l("location changed"+location);
            ((TextView)findViewById(R.id.textLocations))
                    .setText(" Lat"+location.getLatitude()+"\n Lng"+location.getLongitude());
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
    };
}
