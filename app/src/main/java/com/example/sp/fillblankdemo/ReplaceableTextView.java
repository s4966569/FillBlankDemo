package com.example.sp.fillblankdemo;

import android.content.Context;
import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by sp on 17-2-23.
 */

public abstract class ReplaceableTextView extends FrameLayout{
    protected MyTextView mTextView;
    protected RelativeLayout mRelativeLayout;
    protected Context mContext;
    protected Spanned spanned;
    protected EmptySpan[] tagSpans;
    public ReplaceableTextView(Context context) {
        super(context);
        init(context);
    }

    public ReplaceableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ReplaceableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.replaceable_text_view,this,true);
        mTextView = (MyTextView) view.findViewById(R.id.textView);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
        mTextView.setOnDrawFinishedListener(new TextViewOnDrawFinishedListener());
    }

    public void setText(String text){
        spanned = Html.fromHtml(text,getImageGetter(), getTagHandler());
        tagSpans = spanned.getSpans(0,spanned.length(),getTagHandler().getSpanType());
        for(EmptySpan span: tagSpans){
            span.lineHeight = mTextView.getLineHeight();
        }
        mTextView.setText(spanned,TextView.BufferType.SPANNABLE);
    }

    public void replaceSpanWithView() {
        if (mRelativeLayout.getChildCount() > 0) {
            mRelativeLayout.removeAllViews();
        }

        for (EmptySpan s : tagSpans) {

            int start = spanned.getSpanStart(s);

            Layout layout = mTextView.getLayout();

            int line = layout.getLineForOffset(start);

            int topPadding = mTextView.getCompoundPaddingTop();

            int leftMargin = (int) layout.getPrimaryHorizontal(start);

            int descent = layout.getLineDescent(line);

            int base = layout.getLineBaseline(line);

            int spanTop = base + descent - s.height();

            int topMargin = spanTop + topPadding;

            View myView = getReplaceView();

            myView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(s.width(), s.height());

            params.leftMargin = leftMargin;

            params.topMargin = topMargin;

            mRelativeLayout.addView(myView, params);

        }
    }

    public void removeAllReplacementView(){
        if(mRelativeLayout.getChildCount()>0)
            mRelativeLayout.removeAllViews();
    }
    protected Html.ImageGetter getImageGetter(){
        return new HtmlImageGetter(mContext, mTextView);
    }
    protected abstract View getReplaceView();

    protected abstract ReplaceTagHandler getTagHandler();

    private class TextViewOnDrawFinishedListener implements MyTextView.OnDrawFinishedListener{

        @Override
        public void onDrawFinished() {
            replaceSpanWithView();
        }
    }
}
