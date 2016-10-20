package com.codekul.otherservices;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this,MyService.class);
        setContentView(R.layout.activity_main);

        ((VideoView)findViewById(R.id.videoView))
                .setMediaController(new MediaController(this));
        ((VideoView)findViewById(R.id.videoView))
                .setVideoPath(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),"wa.mp4").getAbsolutePath());

        ((VideoView)findViewById(R.id.videoView)).start();


    }

    public void myClick(View view) {
        if(view.getId() == R.id.btnStart)
        startService(intent);
        else if(view.getId() == R.id.btnStop)
            stopService(intent);
    }
}
