package com.example.carl.ui_clicklike;

import android.animation.TypeEvaluator;
import android.graphics.PointF;
/**
 * 自定义路径属性动画
 *
 * */
public class LoveTypeEvaluator implements TypeEvaluator<PointF> {
    private PointF p1,p2;

    public LoveTypeEvaluator(PointF p1, PointF p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public PointF evaluate(float t, PointF p0, PointF p3) {
        //t是[0,1] 开始套公式 公式有四个点
        PointF pointF = new PointF();
        pointF.x = (float) (p0.x*Math.pow((1-t),3)+
                            3*p1.x*t*Math.pow((1-t),2)+
                            3*p2.x*Math.pow(t,2)*(1-t)+
                            p3.x*Math.pow(t,3));

        pointF.y = (float) (p0.y*Math.pow((1-t),3)+
                3*p1.y*t*Math.pow((1-t),2)+
                3*p2.y*Math.pow(t,2)*(1-t)+
                p3.y*Math.pow(t,3));
        return pointF;
    }
}
