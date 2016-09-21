package com.codekul.configchangesonown;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(MainActivity.this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            changeImage(R.drawable.dave);
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            changeImage(R.drawable.bob);
        }
        else {

        }
    }

    private void changeImage(int image) {
        ((ImageView)findViewById(R.id.imageView)).setImageResource(image);
    }
}
