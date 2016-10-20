package com.codekul.otheraidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.codekul.aidlcoomman.IComman;

public class MyService extends Service {

    private MyCommanImpl impl = new MyCommanImpl();

    @Override
    public IBinder onBind(Intent intent) {
        return impl;
    }

    private class MyCommanImpl extends IComman.Stub {

        @Override
        public boolean isValid(String mobile) throws RemoteException {
            return mobile.length() > 10;
        }
    }
}
