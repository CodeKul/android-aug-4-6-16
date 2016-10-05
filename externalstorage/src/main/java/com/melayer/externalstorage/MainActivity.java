package com.melayer.externalstorage;

import android.media.MediaScannerConnection;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isExternalAvailable()){
            // tmrw

            onPublicStorage();

            readFromPublicDirectory();
        }
    }

    private Boolean isExternalAvailable(){
        return  Environment
                .getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
    }

    private void saveOnExternalPrivate(){
        File file = new File(getExternalFilesDir("myDir"),"our.txt");
        Log.i("@codekul","External Path - "+file.getAbsolutePath());
        Log.i("@codekul","Internal Path - "+getFilesDir().getAbsolutePath());

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("This is CodeKul android ".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromPrivateExternal(){

        File file = new File(getExternalFilesDir("myDir"),"our.txt");
        try {
            FileInputStream fis = new FileInputStream(file);
            StringBuilder builder = new StringBuilder();
            while(true){
                int ch = fis.read();
                if(ch == -1) break;
                else builder.append((char)ch);
            }
            Log.i("@codekul",builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onPublicStorage() {
        File file =
                new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"our.xt");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("Hello Welcome to CodeKul".getBytes());
            fos.close();
            MediaScannerConnection.scanFile(this, new String[] {file.toString()}, null, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromPublicDirectory(){
        File file =
                new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"our.xt");

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            StringBuilder builder = new StringBuilder();
            while(true){
                int ch = fis.read();
                if(ch == -1) break;
                else builder.append((char)ch);
            }
            Log.i("@codekul",builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
