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
        paint.setColor(Color.MAGENTA);
        paint.setStrokeWidth(10);
        int n = 20;//n * 2 - 1 is a number of lines
        for(int i = 0; i < n;i++){//diagonal lines
            canvas.drawLine(canvas.getWidth()*(i/n),0,canvas.getWidth(),canvas.getHeight()*(1 - (i/n)),paint);
            canvas.drawLine(0,canvas.getHeight()*(i/n),canvas.getWidth()*(1-(i/n)),canvas.getHeight(),paint);
        }

    }
}