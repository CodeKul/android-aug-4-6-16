package com.codekul.mybluetoth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1587;
    private BluetoothAdapter adapter;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            BluetoothDevice remoteDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            Log.i("@codekul","Remote Name - "+remoteDevice.getName());
            Log.i("@codekul","Remote Address - "+remoteDevice.getAddress());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = BluetoothAdapter.getDefaultAdapter();
        if(adapter == null){
            Log.i("@codekul","We dont have bluetooth");
            return;
        }

        Log.i("@codekul","Address - "+adapter.getAddress());
        Log.i("@codekul","Name - "+adapter.getName());
        
        findViewById(R.id.btnEnable).setOnClickListener(this::clicked);
        findViewById(R.id.btnPaired).setOnClickListener(this::clicked);
        findViewById(R.id.btnDiscover).setOnClickListener(this::clicked);
        findViewById(R.id.btnVisible).setOnClickListener(this::clicked);
        findViewById(R.id.btnServer).setOnClickListener(this::clicked);
        findViewById(R.id.btnClient).setOnClickListener(this::clicked);
    }

    @Override
    protected void onStart() {
        super.onStart();

        registerReceiver(receiver,
                new IntentFilter(BluetoothDevice.ACTION_FOUND));
    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        super.onStop();
    }

    private void clicked(View view) {
        if(view.getId() == R.id.btnEnable) enable();
        if(view.getId() == R.id.btnPaired) pairedDevices();
        if(view.getId() == R.id.btnDiscover) discoverDevices();
        if(view.getId() == R.id.btnVisible) visibleMe();
        if(view.getId() == R.id.btnServer) server();
        if(view.getId() == R.id.btnClient) client();
    }

    private void server() {
        new Thread(this::serverThread).start();
    }

    private void client() {
        new Thread(this::clientThread).start();
    }

    public void clientThread() {

        try {

            BluetoothDevice serverDevice = adapter.getRemoteDevice("84:10:0D:A1:08:ED");
            BluetoothSocket socket = serverDevice
                    .createRfcommSocketToServiceRecord( UUID
                            .fromString("00000000-0000-1000-8000-00805F9B34FB"));
            socket.connect();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("My name is android");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void serverThread(){
        try {
            BluetoothServerSocket serverSocket =
                    adapter.listenUsingInsecureRfcommWithServiceRecord("my-service",
                            UUID.fromString("00000000-0000-1000-8000-00805F9B34FB"));
            BluetoothSocket socket = serverSocket.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String data = dis.readUTF();
            Log.i("@codekul","Data is - "+data);

            new Handler(Looper.getMainLooper())
                    .post(()-> ((TextView)findViewById(R.id.textData)).setText(data));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void visibleMe() {
        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
    }

    private void discoverDevices() {
        adapter.startDiscovery();
    }

    private void enable(){
        if (!adapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    private void pairedDevices(){
        Set<BluetoothDevice> bondedDevices = adapter.getBondedDevices();
        for (BluetoothDevice bondedDevice : bondedDevices) {
            Log.i("@codekul","Name - "+bondedDevice.getName());
            Log.i("@codekul","Address - "+bondedDevice.getAddress());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ENABLE_BT){

            if(resultCode == RESULT_OK) {

            }
        }
    }
}
