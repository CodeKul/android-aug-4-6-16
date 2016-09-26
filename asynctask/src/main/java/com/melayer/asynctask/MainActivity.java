package com.melayer.asynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute(/*Params*/new Integer[]{0,100});
            }
        });
    }

    private void incrementCounter(String text){
        ((TextView)findViewById(R.id.textCounter)).setText(text);
    }
    private void startCounter() {

        for(int i = 0 ; i< 100; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            incrementCounter(""+i);
        }
    }

    private void startWorkerCounter(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                startCounter();
            }
        }).start();
    }

   private class MyTask extends AsyncTask<Integer/*Params*/,Integer/*Progress*/,Boolean/*Result*/> {

       private ProgressDialog dialog;

       @Override
       protected void onPreExecute() {
           super.onPreExecute();

           dialog = ProgressDialog.show(MainActivity.this,"Progress","I love progress :)");

           // UI thread
       }

       @Override
       protected Boolean/*Result*/ doInBackground(Integer... params/*Params*/) {

           for (int i = params[0]; i < params[1]; i++){
               try {
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               publishProgress(/*progress*/i);
           }
           // worker thread
           return null;
       }

       @Override
       protected void onPostExecute(Boolean aBoolean/*Result*/) {
           super.onPostExecute(aBoolean);

           // UI Thread

           dialog.dismiss();
       }

       @Override
       protected void onProgressUpdate(Integer... values/*progress*/) {
           super.onProgressUpdate(values);

           incrementCounter(""+values[0]);
           // UI thread
       }
   }
}
