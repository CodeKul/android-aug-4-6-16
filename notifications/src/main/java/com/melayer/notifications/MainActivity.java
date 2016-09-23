package com.melayer.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATIOIN_SIMPLE = 1234;
    private static final int REQ_SIMPLE = 3456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Simple Toats", Toast.LENGTH_SHORT).show();

                Toast toast = Toast.makeText(MainActivity.this,"Custom",Toast.LENGTH_LONG);
                Button btn = new Button(MainActivity.this);
                toast.setView(btn);
                toast.show();
            }
        });

        findViewById(R.id.btnStatusBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,SimpleActivity.class);

                PendingIntent pendingIntent = PendingIntent
                        .getActivity(MainActivity.this,
                                REQ_SIMPLE,
                                intent,
                                PendingIntent.FLAG_ONE_SHOT);

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("Title")
                        .setContentText("Content")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setContentIntent(pendingIntent)
                        .addAction(R.mipmap.ic_launcher,"Action1",pendingIntent);

                Notification notification  = builder.build();
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    notification = builder.build();
                }*/

                manager.notify(NOTIFICATIOIN_SIMPLE, notification);
            }
        });

        findViewById(R.id.btnDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DialogActivity.class));
            }
        });
    }
}
