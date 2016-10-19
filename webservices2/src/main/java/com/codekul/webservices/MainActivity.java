package com.codekul.webservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btnCall).setOnClickListener(this::callPostGson);
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

    private void callPost(View view) {
        JSONObject dataToSend = new JSONObject();
        try {
            dataToSend.put("title","android");
            dataToSend.put("body","codekul");
            dataToSend.put("userId",100);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Ws.q(this)
                .add(new JsonObjectRequest(Request.Method.POST,
                        "http://jsonplaceholder.typicode.com/posts",
                        dataToSend,
                        this::onResponse,
                        this::onError));
    }

    private void onError(VolleyError volleyError) {
        Log.i("@codekul","Response Error - "+volleyError.toString());
    }

    private void onResponse(JSONObject jsonObject) {
        Log.i("@codekul","Response Json - "+jsonObject.toString());
    }

    private void onStringResponse(String jsonResponse) {
    }

    private void callPostGson(View view){
        Gson gson = new Gson();
        MyReq req = new MyReq();
        req.setBody("Codekul");
        req.setTitle("android");
        req.setUserId(100l);

        Ws.q(this).add(new JsonRequest<MyReq>(Request.Method.POST,"http://jsonplaceholder.typicode.com/posts",gson.toJson(req),res->{
            Log.i("@codekul","Body - "+res.getBody());
        },err->{

        }) {
            @Override
            protected Response<MyReq> parseNetworkResponse(NetworkResponse response) {
                Log.i("@codekul","Post Gson - "+new String(response.data));
                return Response.success(gson.fromJson(new String(response.data),MyReq.class),null);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
        });
    }
}
