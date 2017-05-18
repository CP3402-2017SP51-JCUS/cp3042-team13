package com.example.photoedit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by User on 11/5/2017.
 */

public class MyView extends View {
    private Paint paint;
    private PointF[] points;
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.GREEN);
    }
    public void setPoints(PointF[] points){
        this.points=points;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (PointF point : points) {
            if (point.x != 0 && point.y != 0) {
                RectF rect = new RectF(point.x, point.y, point.x + 10, point.y + 10);
                canvas.drawRect(rect, paint);
            }
        }
    }
}
