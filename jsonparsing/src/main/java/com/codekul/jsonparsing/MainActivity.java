package com.codekul.jsonparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String json = readJson();
        Log.i("@codekul","Json is - "+json);

        Log.i("@codekul","Name is - "+name(json));

        Log.i("@codekul","Cities is - "+cities(json));

        Gson gson = new Gson();
        MyObj obj = gson.fromJson(json, MyObj.class);
        Log.i("@codekul","G Name - "+obj.getName());
        Log.i("@codekul","G Age - "+obj.getAge());
        Log.i("@codekul","G City - "+obj.getCities().get(0));
        Log.i("@codekul","G Obj - "+obj.getObj().getInner());
    }

    private String readJson(){
        StringBuilder json = new StringBuilder();

        try {
            InputStream is = getAssets().open("my.json");
            while(true){
                int ch = is.read();
                if(ch == -1) break;
                else json.append((char)ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    private String name(String json){

        String name = "";
        try {
            JSONObject obj = new JSONObject(json);
            name = obj.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  name;
    }

    private ArrayList<String> cities(String json){
        ArrayList<String> cities = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray arr = obj.getJSONArray("cities");
            for(int i = 0; i < arr.length() ;i++ )
                cities.add(arr.getString(i));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
