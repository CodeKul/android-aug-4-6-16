package com.melayer.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int battery = 0;

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            if(level == -1 || scale == -1) {
                battery =  50;
            }

            battery = (level / scale) * 100;
            changeBatteryLevel(battery);
        }
    };
    private final BroadcastReceiver receiver =
            new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
                        changePowerStatus(R.drawable.ic_battery_charging_full_black_24dp);
                    }
                    else {
                        changePowerStatus(R.drawable.ic_battery_full_black_24dp);
                    }
                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnCustom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.codekul.action.custom");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(receiver, new IntentFilter(filter));

        registerReceiver(batteryReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        unregisterReceiver(batteryReceiver);
        super.onStop();
    }

    private void changePowerStatus(int image){
        ((ImageView)findViewById(R.id.imagePower)).setImageResource(image);
    }

    private void changeBatteryLevel(int battery){
        ((TextView)findViewById(R.id.textBattery)).setText(""+battery);
    }

}
