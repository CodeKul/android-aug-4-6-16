package com.melayer.myinternalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSave)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FileOutputStream fos =
                            openFileOutput("my",MODE_PRIVATE);
                    fos.write("Hello welcome to Android".getBytes());
                    fos.close();
                }
                 catch (Exception e){
                     e.printStackTrace();
                 }
            }
        });

        findViewById(R.id.btnRead).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    // /data/data/package/files/my
                    FileInputStream fis = openFileInput("my");
                    StringBuilder builder = new StringBuilder();
                    while(true){
                        int ch = fis.read();
                        if(ch == -1) break;
                        else builder.append((char)ch);
                    }
                    Log.i("@codekul","Read Internal Storage + \n " + builder.toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btnImp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File filePrivate = getFilesDir();
                Log.i("@codekul","getFilesDir() - "+filePrivate.getAbsolutePath());

               File newDir =  getDir("other",MODE_PRIVATE);
                Log.i("@codekul","getDir - "+newDir.getAbsolutePath());

                String[] fileNames = fileList();
                for (String fileName : fileNames) {
                    Log.i("@codekul","FileName - "+fileName);
                }
            }
        });
    }
}
