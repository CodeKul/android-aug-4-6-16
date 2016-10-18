package com.codekul.webservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btnCall).setOnClickListener(this::callGet);
    }

    private void callGet(View view) {

        Gson gson = new Gson();

        Ws.q(this).add(new StringRequest("http://universities.hipolabs.com/search?name=middle",
                res -> {
                    Log.i("@codekul","Response is "+res);
                    ArrayList<MyRes> list = new ArrayList<>();
                    list = gson.fromJson(res, new TypeToken<List<MyRes>>(){}.getType());
                    Log.i("@codekul","Size - "+list.size());
                    for (MyRes myRes : list) {
                        Log.i("@codekul","Uni Name - "+myRes.getName());
                    }
                },
                this::volleyError));
    }

    private void volleyError(VolleyError volleyError) {
        Log.i("@codekul"," Volly error - "+volleyError.toString());
    }
}
