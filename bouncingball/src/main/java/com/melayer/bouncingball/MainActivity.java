package com.melayer.bouncingball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    private class MyView extends TextView {

        private int mw, mh;
        private float cx,cy, dx,dy,rad;
        private Paint paint;

        public MyView(Context context) {
            super(context);

            dx = dy = 5;
            cx = cy = 0;
            rad = 30;
            paint = new Paint();
            paint.setColor(Color.RED);

            new MyTask().execute();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            mw = getWidth();
            mh = getHeight();

            canvas.drawCircle(cx,cy,rad,paint);
        }

        private class MyTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... params) {

                while(true){
                    if(cx >= mw) dx = -5;
                    if(cx <= 0 ) dx = 5;
                    if(cy >= mh) dy = -5;
                    if(cy <= 0) dy = 5;

                    cx += dx;
                    cy += dy;

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    publishProgress();
                }
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);

                invalidate();
            }
        }
    }
}
