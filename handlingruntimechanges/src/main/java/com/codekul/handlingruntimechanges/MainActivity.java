package com.codekul.handlingruntimechanges;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_MY_DATA = "myData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null)
            ((TextView)findViewById(R.id.textInfo))
                    .setText(savedInstanceState.getString(KEY_MY_DATA));

        findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.textInfo)).setText("Hello Android");
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_MY_DATA,((TextView)findViewById(R.id.textInfo)).getText().toString());
    }

   /* @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null){
            ((TextView)findViewById(R.id.textInfo))
                    .setText(savedInstanceState.getString(KEY_MY_DATA));
        }
    }*/
}
