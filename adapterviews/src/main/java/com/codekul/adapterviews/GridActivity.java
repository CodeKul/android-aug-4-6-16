package com.codekul.adapterviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class GridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        String []dataSet = {"1","2","3","4","5","6","7","8","9","0"};

        ((GridView)findViewById(R.id.girdView))
                .setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataSet));
    }
}
