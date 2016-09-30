package com.melayer.sharedprefs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INT = "myInt";
    private static final String KEY_DOUBLE = "myDouble";
    private static final String KEY_BOOLEAN = "myBoolean";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                writeSharedPrefs();

                readSharedPrefs();
            }
        });
    }

    private void writeSharedPrefs(){
        SharedPreferences prefs = getSharedPreferences("my_file",MODE_PRIVATE);
        //prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_INT,10);
        editor.putBoolean(KEY_BOOLEAN,true);
        editor.putFloat(KEY_DOUBLE,52.96f);
        editor.commit();
    }

    private void readSharedPrefs(){
        SharedPreferences prefs = getSharedPreferences("my_file",MODE_PRIVATE);
        Boolean bool = prefs.getBoolean(KEY_BOOLEAN,true);
        Float flt = prefs.getFloat(KEY_DOUBLE,-2.3f);
        Integer _int = prefs.getInt(KEY_INT,-22);

        Log.i("@codekul","Boolean - "+bool +" Float - "+flt +" Integer - "+_int);
    }
}
