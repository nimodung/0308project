package com.example.exam04_myimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MyImageView extends View {

    int m_x;
    int m_y;
    String m_strAction = "";

    public MyImageView(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setBackgroundResource(R.mipmap.dungdi);

        //페인트 객체 생성, 색상, 크기 초기화
        Paint p = new Paint();
        p.setColor(0xffff0000);
        p.setTextSize(35);

        canvas.drawText("Coordinate : " + m_x + ", " + m_y, 250, 500, p);

        p.setColor(0xffffff00);
        canvas.drawText("Event Action : " + m_strAction, 150, 600, p);
        /*
        Bitmap MyImage = BitmapFactory.decodeResource(getResources(), R.mipmap.dungdi);
        canvas.drawBitmap(MyImage, 0,0,null);
        */
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        m_x = (int)event.getX();
        m_y = (int)event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            m_strAction = "ACTION_DOWN";
        }
        else if(event.getAction() == MotionEvent.ACTION_UP)
        {
            m_strAction = "ACTION_UP";
        }
        else if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            m_strAction = "ACTION_MOVE";
        }
        invalidate();  //onDraw 함수를 호출해줌 (=> 안드로이드에게 화면을 다시 그려달라고 요청)
                        //onDraw 함수는 안드로이드에서 호출하는것

        return true;
    }
}
