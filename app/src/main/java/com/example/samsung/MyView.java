package com.example.samsung;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
public class MyView extends View {
    int N = 100;
    int RADIUS = 30;
    float[]x = new float[N];
    float[]y = new float[N];
    float[]vx = new float[N];
    float[]vy = new float[N];
    float[] Red = new float[N];
    float[] Green = new float[N];
    float[] Blue = new float[N];
    boolean[] check = new boolean[N];
    float maxV = 10, minV = -10;
    Paint paint = new Paint();
    public MyView(Context context) {
        super(context);
    }
    float rand(float min,float max){
        return (float)(Math.random()*(max-min+1)+min);
    }
    void fillRandom(float[] array, float min, float max){
        for(int i = 0; i < array.length;i++){
            array[i] = rand(min,max);
        }
    }
    void fill(float[] array, float value){
        for(int i = 0; i < array.length;i++){
            array[i] = value;
        }
    }

    void addValue(float[] array,float[] value){
        for(int i = 0; i < array.length;i++){
            array[i]+=value[i];
        }
    }

    void fillBoolean(boolean[] array, boolean value){
        for(int i = 0; i < array.length;i++){
            array[i] = value;
        }
    }
    void checkIsBallCrossAnother(){
        for(int i = 0; i < N;i++){
            for(int j = i+1;j<N;j++){
                if(Math.sqrt((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]))<=2*RADIUS){
                    x[i] = rand(0,this.getWidth()-1);
                    y[i] = rand(0,this.getHeight()-1);
                    i = 0; j = 0;
                }
            }
        }
    }
    void checkIsBallFell(){
        for(int i = 0; i < N;i++){
            for(int j = i+1; j < N;j++){
                if(Math.sqrt((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]))<=2*RADIUS){
                    vy[i] = 0;
                    vy[j] = 0;
                    check[i]=true;
                    check[j]=true;
                }
            }
            if(y[i]+RADIUS>=this.getHeight()){
                vy[i]=0;
                check[i] = true;
            }
        }
    }
    void checkIsAllBallsFell(){
        for(int i = 0; i < N;i++){
            if(!check[i]){
                break;
            }
            if(i==N-1){
                whileTheyAreFalling = false;
            }
        }
    }
    void checkIsBallCrash(){
        for(int i = 0; i < N;i++){
            for(int j = i+1; j < N;j++){
                if(Math.sqrt((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]))<=2*RADIUS){
                    vx[i] = rand(minV,maxV);
                    vy[i] = rand(minV,maxV);
                    vx[j] = vx[i]*(-1);
                    vy[j] = vy[i]*(-1);
                    Red[i] = rand(0,254);
                    Green[i] = rand(0,254);
                    Blue[i] = rand(0,254);
                    Red[j] = rand(0,254);
                    Green[j] = rand(0,254);
                    Blue[j] = rand(0,254);
                }
            }
            if(x[i]-RADIUS<=0||x[i]+RADIUS>=this.getWidth()){
                vx[i]*=(-1);
                Red[i] = rand(0,254);
                Green[i] = rand(0,254);
                Blue[i] = rand(0,254);
            }
            if(y[i]-RADIUS<=0||y[i]+RADIUS>=this.getHeight()){
                vy[i]*=(-1);
                Red[i] = rand(0,254);
                Green[i] = rand(0,254);
                Blue[i] = rand(0,254);
            }
        }
    }

    void drawBalls(Canvas canvas){
        for (int i = 0; i < N; i++) {
            paint.setColor(Color.rgb((int)Red[i],(int)Green[i],(int)Blue[i]));
            canvas.drawCircle(x[i], y[i], RADIUS, paint);
        }
    }

    boolean isStart = false;
    boolean isBeforeStart = false;
    boolean whileTheyAreFalling = true;
    @Override
    protected void onDraw(Canvas canvas){
        if(!isBeforeStart) {
            fillRandom(x,0,this.getWidth()-1);
            fillRandom(y,0,this.getHeight()-1);
            fill(vx,0);
            fill(vy,9.8f);
            fillRandom(Red,0,254);
            fillRandom(Green,0,254);
            fillRandom(Blue,0,254);
            fillBoolean(check,false);
            checkIsBallCrossAnother();
            isBeforeStart = true;
        }
        else if(whileTheyAreFalling) {
            checkIsBallFell();
            checkIsAllBallsFell();
        }
        else if(!isStart){
            fillRandom(vx,maxV,minV);
            fillRandom(vy,minV,maxV);
            isStart = true;
        }
        else{
            checkIsBallCrash();
        }

        drawBalls(canvas);

        addValue(x,vx);
        addValue(y,vy);

        invalidate();

    }
}