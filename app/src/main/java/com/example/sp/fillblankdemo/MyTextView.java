package com.example.sp.fillblankdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by sp on 17-3-2.
 */

public class MyTextView extends TextView {
    OnDrawFinishedListener onDrawFinishedListener;
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(onDrawFinishedListener != null){
            onDrawFinishedListener.onDrawFinished();
        }
    }

    public OnDrawFinishedListener getOnDrawFinishedListener() {
        return onDrawFinishedListener;
    }

    public void setOnDrawFinishedListener(OnDrawFinishedListener onDrawFinishedListener) {
        this.onDrawFinishedListener = onDrawFinishedListener;
    }

    public interface OnDrawFinishedListener{
        void onDrawFinished();
    }
}
