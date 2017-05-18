package com.example.spiritlevel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.math.BigDecimal;

/**
 * Created by User on 17/5/2017.
 */

public class Display extends View {
    private Paint paint;
    private float Xpoints;
    private float Ypoints;
    private Bitmap xBall,yBall;
    public Display(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.GREEN);
        xBall= BitmapFactory.decodeResource(context.getResources(),R.drawable.balls);
        yBall= BitmapFactory.decodeResource(context.getResources(),R.drawable.balls);
    }
    private PointF getCenter(){
        return new PointF(getWidth()/2f,getHeight()/2f);
    }
    public void setPoints(float Xpoints, float Ypoints ){
        this.Xpoints=(Xpoints);this.Ypoints=(Ypoints);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        PointF center=getCenter();
        Float x=center.x-32+(Xpoints*38);
        Float y=center.y-32+(40*Ypoints);
        if(x>=getWidth()){
            x=getWidth()-32f;
        }else if(x<=0){
            x=64f;
        }
        if(y>=getHeight()){
            y=getHeight()-32f;
        }else if(y<=0){
            y=32f;
        }
        canvas.drawBitmap(xBall,x,center.y+250,paint);
        canvas.drawBitmap(yBall,center.x+250,y,paint);
//        canvas.drawLine(0,center.y,getWidth(),center.y,paint);
//        canvas.drawLine(center.x,0,center.x,getHeight(),paint);
//        canvas.drawRect(center.x+(Xpoints*20)-50,center.y+(Ypoints*20)-50,center.x+(Xpoints*20)+50,center.y+(Ypoints*20)+50 ,paint);
    }


}
