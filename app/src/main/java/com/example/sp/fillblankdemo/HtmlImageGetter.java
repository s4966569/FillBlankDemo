package com.example.sp.fillblankdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.util.HashMap;

/**
 * Created by cailei on 05/01/2017.
 */

public class HtmlImageGetter implements Html.ImageGetter {
    Context mContext;
    TextView mTextView;
    HashMap<String, UrlDrawable> mMap;
    ImageGetterListener imageGetterListener ;

    HtmlImageGetter(Context context, TextView textview) {
        mContext = context;
        mTextView = textview;
        mMap = new HashMap<>();
    }

    @Override
    public Drawable getDrawable(final String source) {
        UrlDrawable drawable = new UrlDrawable();
        mMap.put(source, drawable);
        drawable.setBounds(0, 0, 0, 0);
        Glide.with(mContext)
                .load(source)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        UrlDrawable drawable = mMap.get(source);
                        float factor = resource.getIntrinsicHeight() / 200; // 最宽 200
                        float width = resource.getIntrinsicWidth();
                        float height = resource.getIntrinsicHeight();
                        if (factor > 1) {
                            width /= factor;
                            height /= factor;
                        }

                        drawable.setBounds(0, 0, Math.round(width), Math.round(height));
                        drawable.drawable = resource;
                        drawable.drawable.setBounds(0, 0, Math.round(width), Math.round(height));
                        mTextView.setText(mTextView.getText());
                    }
                });

//        Glide.with(mContext).load(source).listener(new RequestListener<String, GlideDrawable>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                UrlDrawable drawable = mMap.get(source);
//                float factor = resource.getIntrinsicHeight() / 200; // 最宽 200
//                float width = resource.getIntrinsicWidth();
//                float height = resource.getIntrinsicHeight();
//                if (factor > 1) {
//                    width /= factor;
//                    height /= factor;
//                }
//
//                drawable.setBounds(0, 0, Math.round(width), Math.round(height));
//                drawable.drawable = resource;
//                drawable.drawable.setBounds(0, 0, Math.round(width), Math.round(height));
//                mTextView.setText(mTextView.getText());
//                return false;
//            }
//        });
        return drawable;
    }

    public ImageGetterListener getImageGetterListener() {
        return imageGetterListener;
    }

    public void setImageGetterListener(ImageGetterListener imageGetterListener) {
        this.imageGetterListener = imageGetterListener;
    }

    public class UrlDrawable extends BitmapDrawable {
        // the drawable that you need to set, you could set the initial drawing
        // with the loading image if you need to
        protected Drawable drawable;

        @Override
        public void draw(Canvas canvas) {
            // override the draw to facilitate refresh function lat
            // er
            if(drawable != null) {
                drawable.draw(canvas);
            }
        }
    }

    public interface ImageGetterListener{
        void onSuccess(Drawable drawable);
        void onFailed(String error);
    }
}
