package com.codekul.simpleapp;

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

        findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MyTask().execute(/*params*/new Integer[]{100});
            }
        });
    }
    public void commanLogic(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0 ; i < 100; i++){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ((TextView)findViewById(R.id.textView)).setText(""+i); //wrong
                }
            }
        }).start();
    }

    private final class MyTask extends AsyncTask<Integer,Integer,Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // UI
        }

        @Override
        protected Boolean/*Result*/ doInBackground(Integer... params/*Params*/) {
            // worker thread

            for (int i = 0 ; i < params[0] ;i++) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(new Integer[]{i});
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean/*Result*/) {
            super.onPostExecute(aBoolean);

            //ui thread
        }

        @Override
        protected void onProgressUpdate(Integer... values/*Progress*/) {
            super.onProgressUpdate(values);

            //ui thread

            ((TextView)findViewById(R.id.textView)).setText(""+values[0]);
        }
    }
}
