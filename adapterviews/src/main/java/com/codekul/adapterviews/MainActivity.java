package com.codekul.adapterviews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         gridView();
    }

    private void customAdapter(){
        ArrayList<MyItem> items  = new ArrayList<>();
        items.add(new MyItem(1l,R.mipmap.ic_launcher,"Android"));
        items.add(new MyItem(2l,R.mipmap.ic_launcher,"Apple"));
        items.add(new MyItem(3l,R.mipmap.ic_launcher,"Symbian"));
        items.add(new MyItem(4l,R.mipmap.ic_launcher,"Rim"));

        ((ListView)findViewById(R.id.listCountries)).setAdapter(new MyAdapter(this,items));
    }


    private void arraAdapter(){
        ArrayList<String> dataSet = new ArrayList<>();
        dataSet.add("India");
        dataSet.add("China");
        dataSet.add("Japan");
        dataSet.add("Russia");
        dataSet.add("Australia");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataSet);

        ((ListView)findViewById(R.id.listCountries)).setAdapter(adapter);
    }

    private void gridView(){

        startActivity(new Intent(this,GridActivity.class));
    }
}
