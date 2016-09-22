package com.melayer.assetsandresources;

import android.content.res.AssetManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int appVersion = getResources().getInteger(R.integer.appVersion);
        Log.i("@codekul","App Version - "+appVersion);

        int color = getResources().getColor(R.color.pureWhite);
        color = ResourcesCompat.getColor(getResources(),R.color.pureWhite,null);
        color = ContextCompat.getColor(this,R.color.pureWhite);

        AssetManager manager = getAssets();
        try {
            InputStream is = manager.open("my.txt");
            StringBuilder builder = new StringBuilder();
            //String str = "";
            while(true){
                int ch = is.read();
                if(ch == -1) break;
                else builder.append((char)ch); /*str += ch;*/
            }

            Log.i("@codekul","Asset Data is - "+builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
