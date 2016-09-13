package com.codekul.webservices;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wsLoadAllCountries();
            }
        });
    }

    private void wsLoadAllCountries(){

        final ProgressDialog dialog = ProgressDialog.show(this,"Countries","Loading Countres");
        Ws.q(this).add(new JsonObjectRequest(Request.Method.GET, "http://services.groupkt.com/country/get/all", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                dialog.dismiss();
                onLoadAllCountries(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
            }
        }));
    }

    private void onLoadAllCountries(JSONObject response){

        if(response != null) {
            Log.i("@codekul", "Raw - " + response.toString());

            My my = convertJson(response);
            createList(my);
        }
    }

    private My convertJson(JSONObject response){

        My my = new My();
        ObjectMapper mapper = new ObjectMapper();
        try {
            my = mapper.readValue(response.toString(),
                    My.class);
            Log.i("@codekul", "Object - " + my.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return my;
    }

    private void createList(My my){
        if(my != null){

            ArrayList<String> dataSet = new ArrayList<>();

            for (Result result : my.getRestResponse().getResult())
                dataSet.add(result.getName());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataSet);
            ((ListView)findViewById(R.id.listCountries)).setAdapter(adapter);
        }
    }
}
