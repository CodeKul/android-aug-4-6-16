package com.codekul.otherservices;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;
import java.io.IOException;

public class MyService extends Service {

    private MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(() ->{
            player = new MediaPlayer();
            try {
                player.setDataSource(new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"My.mp3").getAbsolutePath());
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.start();
        }).start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        player.stop();
        player.release();

        super.onDestroy();
    }
}
