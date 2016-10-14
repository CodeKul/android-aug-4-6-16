package com.codekul.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.codekul.comman.IComman;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IComman calc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnBind).setOnClickListener(this::bindIt);

        findViewById(R.id.btnCalc).setOnClickListener(this::calculate);
    }

    private void bindIt(View v){

        Intent intent = new Intent("com.codekul.binder.COMMAN");
        bindService(convertImplicitIntentToExplicitIntent(intent), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                calc = IComman.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                calc = null;

            }
        },BIND_AUTO_CREATE);
    }

    private void calculate(View v){
        try {
            Log.i("@codekul","Calculation is - "+calc.calc(10,121));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Intent convertImplicitIntentToExplicitIntent(Intent implicitIntent) {
        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicitIntent, 0);

        if (resolveInfoList == null || resolveInfoList.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfoList.get(0);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
}
