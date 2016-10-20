package com.codekul.otheraidlclient;

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

import com.codekul.aidlcoomman.IComman;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IComman comman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btnBind).setOnClickListener(this::bind);
        findViewById(R.id.btnValidate).setOnClickListener(this::validate);
    }

    private void validate(View view) {
        try {
            Log.i("@codekul","Is Mobile Valid ? "+ comman.isValid("+919762548833"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void bind(View view) {

        bindService(convertImplicitIntentToExplicitIntent(new Intent("com.codekul.action.MY_SERVICE")), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                comman = IComman.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                comman = null;
            }
        }, BIND_AUTO_CREATE);
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
