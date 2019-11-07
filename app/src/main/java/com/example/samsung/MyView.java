package com.example.samsung;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
    Paint paint = new Paint();
    int N = 100;
    int RADIUS = 30;
    float[] x = new float[N];
    float[] y = new float[N];
    float[] vx = new float[N];
    float[] vy = new float[N];
    int[] Red = new int[N];
    int[] Green = new int[N];
    int[] Blue = new int[N];
    boolean[] check = new boolean[N];
    boolean isStart = false;
    boolean isBeforeStart = false;
    boolean whileTheyAreFalling = true;


    public MyView(Context context) {
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas){
        if(!isBeforeStart) {
            for(int i = 0; i < N;i++) {
                x[i] = (float) (Math.random() * this.getWidth());
                y[i] = (float) (Math.random() * this.getHeight());
                vx[i] = 0;
                vy[i] = 9.8f;
                Red[i] = (int) (Math.random()*255);
                Green[i] = (int) (Math.random()*255);
                Blue[i] = (int) (Math.random()*255);
                check[i]=false;
            }
            for(int i = 0; i < N;i++){
                for(int j = i+1;j<N;j++){
                    if(Math.sqrt((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]))<=2*RADIUS){
                        x[i] = (float) (Math.random() * this.getWidth());
                        y[i] = (float) (Math.random() * this.getHeight());
                        i = 0;
                        j = 0;
                    }
                }
            }
            isBeforeStart = true;
        }
        else if(whileTheyAreFalling) {
            for(int i = 0; i < N;i++){
                for(int j = i+1; j < N;j++){
                    if(Math.sqrt((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]))<=2*RADIUS){
                        vy[i] = 0;
                        vy[j] = 0;
                        check[i]=true;
                        check[j]=true;
                    }
                }
                if(y[i]>=this.getHeight()){
                    vy[i]=0;
                    check[i] = true;
                }
            }
                    for(int i = 0; i < N;i++){
                        if(!check[i]){
                            break;
                        }
                        if(i==N-1){
                            whileTheyAreFalling = false;
                        }
                    }
        }
        else if(!isStart){
            for(int i = 0; i < N;i++){
                vx[i] = (float)(Math.random() * 20 - 10);
                vy[i] = (float)(Math.random() * 20 - 10);
            }
            isStart = true;
        }
        else{
            for(int i = 0; i < N;i++){
                for(int j = i+1; j < N;j++){
                    if(Math.sqrt((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]))<=2*RADIUS) {
                        vx[i] = (float) (Math.random() * 20 - 10);
                        vy[i] = (float) (Math.random() * 20 - 10);
                        vx[j] = vx[i] * (-1);
                        vy[j] = vy[i] * (-1);
                        Red[i] = (int) (Math.random() * 255);
                        Green[i] = (int) (Math.random() * 255);
                        Blue[i] = (int) (Math.random() * 255);
                        Red[j] = (int) (Math.random() * 255);
                        Green[j] = (int) (Math.random() * 255);
                        Blue[j] = (int) (Math.random() * 255);
                    }

                }
                if(x[i]-RADIUS<=0||x[i]+RADIUS>=this.getWidth()){
                    vx[i]*=(-1);
                    Red[i] = (int) (Math.random()*255);
                    Green[i] = (int) (Math.random()*255);
                    Blue[i] = (int) (Math.random()*255);
                }
                if(y[i]-RADIUS<=0||y[i]+RADIUS>=this.getHeight()){
                    vy[i]*=(-1);
                    Red[i] = (int) (Math.random()*255);
                    Green[i] = (int) (Math.random()*255);
                    Blue[i] = (int) (Math.random()*255);
                }
            }

        }
        for (int i = 0; i < N; i++) {
            paint.setColor(Color.rgb(Red[i],Green[i],Blue[i]));
            canvas.drawCircle(x[i], y[i], RADIUS, paint);
        }
        for (int i = 0; i < N; i++) {
            x[i] += vx[i];
            y[i] += vy[i];
        }


    invalidate();
    }

}
