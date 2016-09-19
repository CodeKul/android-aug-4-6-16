package com.codekul.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_BT_ON = 1234;

    private BroadcastReceiver receiverStateChanged = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i("@codekul","BT Action - "+intent.getAction());
            if(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,-1) == BluetoothAdapter.STATE_ON){
                Log.i("@codekul","BT On");
            }
            else if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,-1) == BluetoothAdapter.STATE_TURNING_ON) {
                Log.i("@codekul","BT Turning On");
            }
            else if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,-1) == BluetoothAdapter.STATE_TURNING_OFF){
                Log.i("@codekul","BT Turning Off");
            }
            else {
                Log.i("@codekul","BT Off");
            }
        }
    };

    private BroadcastReceiver receiverDiscovery = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

             BluetoothDevice remoteDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            Log.i("@codekul","Name - "+remoteDevice.getName()+" Add - "+remoteDevice.getAddress());
        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        if(adapter == null){
            Toast.makeText(this,"No bluetooth support",Toast.LENGTH_LONG).show();
            return;
        }
        Log.i("@codekul","My Address - "+adapter.getAddress());
        Log.i("@codekul","My Name - "+adapter.getName());

        if(!adapter.isEnabled()) startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQ_BT_ON);

        registerReceiver(receiverStateChanged, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
        registerReceiver(receiverDiscovery,new IntentFilter(BluetoothDevice.ACTION_FOUND));

        getBondedDevices(adapter);

        findViewById(R.id.btnVisibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent discoverableIntent = new
                        Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                startActivity(discoverableIntent);
            }
        });

        findViewById(R.id.btnDiscover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.startDiscovery();
            }
        });

        findViewById(R.id.btnServer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            BluetoothServerSocket bss =
                                    adapter.listenUsingInsecureRfcommWithServiceRecord("simple", UUID.fromString("00000000-0000-1000-8000-00805F9B34FB"));
                            BluetoothSocket bs = bss.accept();
                            Log.i("@codekul","Client Connected");
                            DataOutputStream dos = new DataOutputStream(bs.getOutputStream());
                            dos.writeUTF("hello");
                            //dos.close();
                            //bs.close();
                            //bss.close();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        findViewById(R.id.btnClient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //02:00:00:00:00:00
                //metigation

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try{
                            BluetoothDevice server = adapter.getRemoteDevice("84:10:0D:A1:08:ED");
                            BluetoothSocket socket = server.createRfcommSocketToServiceRecord(UUID.fromString("00000000-0000-1000-8000-00805F9B34FB"));
                            socket.connect();

                            DataInputStream dis = new DataInputStream(socket.getInputStream());
                            Log.i("@codekul","Server Ssay - "+dis.readUTF());
                            //dis.close();
                            //socket.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private void getBondedDevices(BluetoothAdapter adapter){
        Set<BluetoothDevice> devices = adapter.getBondedDevices();

        for (BluetoothDevice device : devices) {
            Log.i("@codekul", "Name - "+device.getName() +" Address "+device.getAddress());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_BT_ON){
            if(resultCode ==RESULT_OK) Log.i("@codekul","Bluetooth Enabled");
            else Log.i("@codekul","Bluetooth Disabled");
        }
    }
}
