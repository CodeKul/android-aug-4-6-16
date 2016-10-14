package com.codekul.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.codekul.comman.IComman;

public class MyService extends Service {

    private IComman.Stub impl = new IComman.Stub() {
        @Override
        public int calc(int num1, int num2) throws RemoteException {
            return num1+num2;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return impl;
    }
}
