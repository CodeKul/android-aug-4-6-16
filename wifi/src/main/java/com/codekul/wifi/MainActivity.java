package com.codekul.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
            List<ScanResult> list = manager.getScanResults();
            for (ScanResult scanResult : list) {
                Log.i("@codekul","Scanned - "+scanResult.SSID);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
        for (WifiConfiguration wifiConfiguration : manager.getConfiguredNetworks()) {
            Log.i("@codekul","SSID - "+wifiConfiguration.SSID);
            Log.i("@codekul","BSSID - "+wifiConfiguration.BSSID);
        }

        findViewById(R.id.btnScan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.startScan();
            }
        });

        findViewById(R.id.btnConnect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiConfiguration wifiConfig = new WifiConfiguration();
                wifiConfig.SSID = String.format("\"%s\"", "YOUR CODEKUL");
                wifiConfig.preSharedKey = String.format("\"%s\"", "#codeguru#;");

                WifiManager wifiManager = (WifiManager)getSystemService(WIFI_SERVICE);
//remember id
                int netId = wifiManager.addNetwork(wifiConfig);
                wifiManager.disconnect();
                wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();
            }
        });

        registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }
}
