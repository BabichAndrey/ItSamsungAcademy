package com.example.samsung;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Paint paint1 = new Paint();
        paint.setColor(Color.RED);
        paint1.setColor(Color.BLUE);

        //red rhombuses
        canvas.drawLine(canvas.getWidth() / 4,0,0,canvas.getHeight() / 4,paint);
        canvas.drawLine(0,canvas.getHeight() / 4,canvas.getWidth()*(3/4),canvas.getHeight(),paint);
        canvas.drawLine(canvas.getWidth() / 4,0,canvas.getWidth(),canvas.getHeight()*(3/4),paint);
        canvas.drawLine(canvas.getWidth(),canvas.getHeight()*(3 / 4),canvas.getWidth()*(3 / 4),canvas.getHeight(),paint);
        canvas.drawLine(canvas.getWidth() * (3 / 4),0,0,canvas.getHeight() * (3 / 4),paint);
        canvas.drawLine(canvas.getWidth() * (3 / 4),0,canvas.getWidth(),canvas.getHeight() / 4,paint);
        canvas.drawLine(canvas.getWidth(),canvas.getHeight() / 4 ,canvas.getWidth() / 4 ,canvas.getHeight(),paint);
        canvas.drawLine(0,canvas.getHeight() * (3 / 4),canvas.getWidth() / 4,canvas.getHeight(),paint);


        //blue rhombuses
        canvas.drawLine(canvas.getWidth() / 2 ,0,canvas.getWidth(),canvas.getHeight() / 2,paint1);
        canvas.drawLine(canvas.getWidth() / 2 ,0,0,canvas.getHeight() / 2,paint1);
        canvas.drawLine(canvas.getWidth() / 2,canvas.getHeight(),0,canvas.getHeight() / 2,paint1);
        canvas.drawLine(canvas.getWidth() / 2,canvas.getHeight(),canvas.getWidth(),canvas.getHeight() / 2,paint1);
        canvas.drawLine(canvas.getWidth() / 4,canvas.getHeight() / 4,canvas.getWidth() * (3 / 4),canvas.getHeight() * (3 / 4),paint1);
        canvas.drawLine(canvas.getWidth() * (3 / 4),canvas.getHeight() / 4,canvas.getWidth() / 4,canvas.getHeight() * (3 / 4),paint1);

    }
}