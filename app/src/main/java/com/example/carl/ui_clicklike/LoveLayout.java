package com.example.carl.ui_clicklike;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Interpolator;
import android.graphics.Point;
import android.graphics.PointF;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

public class LoveLayout extends RelativeLayout {
    private Random mRandom;
    private int [] mColors;
    private int mWidth;
    private int mHeight;
    private int mCircleHeight;
    private int mCircleWidth;
    private TimeInterpolator[] mInterpolator;
    public LoveLayout(Context context) {
        this(context,null);
    }

    public LoveLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRandom = new Random();
        mColors = new int[]{0x55FF0000,0x5500ff00,0x550000ff};
        final CircleView circleView = new CircleView(getContext());
        circleView.post(new Runnable() {
            @Override
            public void run() {
                mCircleHeight=circleView.getHeight();
                mCircleWidth = circleView.getHeight();
            }
        });
        circleView.setVisibility(INVISIBLE);
        addView(circleView);
        mInterpolator = new TimeInterpolator[]{new AccelerateDecelerateInterpolator(),new AccelerateInterpolator(),
        new DecelerateInterpolator(),new LinearInterpolator()};
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    public void addLove(){
        //ImageView loveIv = new ImageView(getContext());
        final CircleView circleView = new CircleView(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        circleView.setColor(mColors[mRandom.nextInt(mColors.length)]);
        circleView.setLayoutParams(params);
        addView(circleView);
        //添加效果 有放大和透明度变化
        AnimatorSet animator = getAnimator(circleView);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //执行完后移除
                removeView(circleView);

            }
        });

        animator.start();




    }

    private AnimatorSet getAnimator(CircleView cv) {
        AnimatorSet allAnimatorSet = new AnimatorSet();
        //添加效果，有放大和透明度的变化
        AnimatorSet innerAnimator=new AnimatorSet();
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(cv,"alpha",0.3f,1f);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(cv,"scaleX",0.3f,1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(cv,"scaleY",0.3f,1f);

        innerAnimator.playTogether(alphaAnimator,scaleXAnimator,scaleYAnimator);
        innerAnimator.setDuration(350);
        //innerAnimator.start();
        //运行路径动画
        allAnimatorSet.playSequentially(innerAnimator,getBezierAnimator(cv));
        return allAnimatorSet;
    }

    private Animator getBezierAnimator(final CircleView cv) {
        PointF p0 = new PointF(mWidth/2-mCircleWidth/2,mHeight-mCircleHeight);
        PointF p1 = getPoint(1);
        PointF p2 = getPoint(2);

        PointF p3 = new PointF(mRandom.nextInt(mWidth-mCircleWidth),0);
        LoveTypeEvaluator typeEvaluator = new LoveTypeEvaluator(p1,p2);
        ValueAnimator bezierAnimator = ObjectAnimator.ofObject(typeEvaluator,p0,p3);
        //随机加一些差值器
        bezierAnimator.setInterpolator(mInterpolator[mRandom.nextInt(mInterpolator.length)]);
        bezierAnimator.setDuration(3000);
        bezierAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF)animation.getAnimatedValue();
                cv.setX(pointF.x);
                cv.setY(pointF.y);
                float t = animation.getAnimatedFraction();
                cv.setAlpha(1-t+0.2f);


            }
        });
        return bezierAnimator;
    }

    private PointF getPoint(int index) {
        return new PointF(mRandom.nextInt(mWidth-mCircleWidth),mRandom.nextInt(mHeight/2)+(index-1)*(mHeight)/2);
    }
}














