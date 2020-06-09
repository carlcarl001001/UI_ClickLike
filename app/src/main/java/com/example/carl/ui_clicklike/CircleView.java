package com.example.carl.ui_clicklike;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {
    private int circle_radius=40;
    private Paint mPaint;
    private Paint mBorderPaint;
    private int mBorderWidth = 2;
    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);//设置为空心圆
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setColor(Color.WHITE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //指定本控件的宽高
        int width = circle_radius*2+mBorderWidth*2;
        int height = circle_radius*2+mBorderWidth*2;
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        canvas.drawCircle(circle_radius,circle_radius,circle_radius,mPaint);
        canvas.drawCircle(circle_radius,circle_radius,circle_radius,mBorderPaint);
    }
    public void setColor(int color){
        mPaint.setColor(color);
    }
}
