package com.melayer.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DialogActivity extends AppCompatActivity {

    public static final String TAG_ALERT = "alert";
    public static final String TAG_DATE = "datePicker";
    public static final String TAG_TIME = "timePicker";
    public static final String TAG_PROGRESS = "progress";
    public static final String TAG_CUSTOM = "custom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findViewById(R.id.btnAlert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TAG_ALERT);
            }
        });
        findViewById(R.id.btnDatePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TAG_DATE);
            }
        });
        findViewById(R.id.btnTimePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TAG_TIME);
            }
        });
        findViewById(R.id.btnProgress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TAG_PROGRESS);
            }
        });
        findViewById(R.id.btnCustom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TAG_CUSTOM);
            }
        });
    }

    private void showDialog(String tag){
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),tag);
    }
}
