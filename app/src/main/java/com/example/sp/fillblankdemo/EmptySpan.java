package com.example.sp.fillblankdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

/**
 * Created by cailei on 05/01/2017.
 */

public class EmptySpan extends DynamicDrawableSpan {
    public int lineHeight;
    public int width = 200;
    public int height = 180;

    @Override
    public Drawable getDrawable() {
        Drawable d = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                Paint p = new Paint();
                p.setColor(Color.GREEN);
                canvas.drawRect(0,0,width,height,p);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.OPAQUE;
            }
        };
        d.setBounds(0, 0, width, height);
        return d;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        if (fm != null) {
            if (height > lineHeight) {
                int orgDescent = fm.descent;
                int extraSpace = height - (fm.descent - fm.ascent);
                fm.descent = extraSpace / 2 + orgDescent;
                fm.bottom = fm.descent;
                fm.ascent = -height + fm.descent;
                fm.top = fm.ascent;
            }
        }
        return width;
    }
}
