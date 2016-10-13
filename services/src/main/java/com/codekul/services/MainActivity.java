package com.codekul.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*findViewById(R.id.btnStart).setOnClickListener( v -> {
            //lambda expression

        });*/

        findViewById(R.id.btnStart)
                .setOnClickListener(this::startMusicService);

        findViewById(R.id.btnStop)
                .setOnClickListener(this::stopMusic);
    }

    private void startMusicService(View view){
        startService(new Intent(this, MyService.class));
    }

    private void stopMusic(View view){
        stopService(new Intent(this, MyService.class));
    }
}
