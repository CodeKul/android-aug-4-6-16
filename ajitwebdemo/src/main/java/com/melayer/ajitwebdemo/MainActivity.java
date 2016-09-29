package com.melayer.ajitwebdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Gson gson = new Gson();

        findViewById(R.id.btnPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ws.queue(MainActivity.this).add(new JsonObjectRequest("http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        setText(response.toString());

                        GeoNames names = gson.fromJson(response.toString(),GeoNames.class);

                        for (Geo geo : names.getGeonames()) {
                            Log.i("@codekul","Geo - "+geo.getFclName());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }));
            }
        });
    }

    private void setText(String msg){
        ((TextView)findViewById(R.id.textResponse)).setText(msg);
    }
}
