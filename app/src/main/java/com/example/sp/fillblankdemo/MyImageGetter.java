package com.example.sp.fillblankdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.HashMap;

/**
 * Created by sp on 17-2-24.
 */

public class MyImageGetter implements Html.ImageGetter {
    private Context mContext;
    private TextView textView;
    private HashMap<String,Drawable> hashMap = new HashMap<>();

    public MyImageGetter(Context mContext, TextView textView) {
        this.mContext = mContext;
        this.textView = textView;
    }

    @Override
    public Drawable getDrawable(final String source) {
        Drawable d = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                Paint p = new Paint();
                p.setColor(Color.RED);
                canvas.drawRect(0,0,80,80,p);
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
        d.setBounds(0,0,80,80);
        return d;
    }
}
