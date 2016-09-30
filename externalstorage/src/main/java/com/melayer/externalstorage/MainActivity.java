package com.melayer.externalstorage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isExternalAvailable()){
            // tmrw
        }
    }

    private Boolean isExternalAvailable(){
        return  Environment
                .getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
    }
}
