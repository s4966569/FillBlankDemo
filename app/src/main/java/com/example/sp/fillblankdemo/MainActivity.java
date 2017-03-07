package com.example.sp.fillblankdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

public class MainActivity extends Activity {

    private MyTextView tv;
    private RelativeLayout rlMaskView;
    private ImageView bottomImageView;
    private FrameLayout fl_content;
    Spanned spannedStr;
    EmptySpan[] spans;
    private Button btn_jump;
    private HashMap<EmptySpan,View> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (MyTextView) findViewById(R.id.tv_top);
        btn_jump = (Button) findViewById(R.id.btn_jump);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        String str = FileUtils.fetchFileContent(this, "html.txt");

        HtmlImageGetter htmlImageGetter = new HtmlImageGetter(tv.getContext(), tv);
        MyImageGetter myImageGetter = new MyImageGetter(this, tv);
        spannedStr = Html.fromHtml(str, htmlImageGetter, new MyTagHandler());
        spans = spannedStr.getSpans(0, spannedStr.length(), EmptySpan.class);
        for (EmptySpan s : spans) {
            s.lineHeight = tv.getLineHeight();
        }
        tv.setText(spannedStr, TextView.BufferType.SPANNABLE);
//        tv.setText(spannedStr);
        rlMaskView = (RelativeLayout) findViewById(R.id.rl_top_mask);
        bottomImageView = (ImageView) findViewById(R.id.iv_bottom);


        Glide.with(this)
                .load("http://scc.jsyxw.cn/image/20160812/1470975461952608.png")
                .into(bottomImageView);
        bottomImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityB.class);
                startActivity(intent);
            }
        });
        btn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityA.class);
                startActivity(intent);
            }
        });

        tv.setOnDrawFinishedListener(new MyTextView.OnDrawFinishedListener() {
            @Override
            public void onDrawFinished() {
//                replaceView(spannedStr);
//                replaceViewEx(spannedStr);
                replaceViewEx(spannedStr);
            }
        });

    }

    private void replaceView(Spanned spannedStr) {
        if (rlMaskView.getChildCount() > 0) {
            rlMaskView.removeAllViews();
        }
        for (EmptySpan s : spans) {

            int start = spannedStr.getSpanStart(s);
            Layout layout = tv.getLayout();
            int line = layout.getLineForOffset(start);
            int topPadding = tv.getCompoundPaddingTop();
            int leftMargin = (int) layout.getPrimaryHorizontal(start);
            int descent = layout.getLineDescent(line);
            int base = layout.getLineBaseline(line);
            int spanTop = base + descent - s.height();
            int topMargin = spanTop + topPadding;

            EditText myView = new EditText(rlMaskView.getContext());
            myView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            myView.setSingleLine();
            myView.setText("2222");
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(s.width(), s.height());
            params.leftMargin = leftMargin;
            params.topMargin = topMargin;
            rlMaskView.addView(myView, params);
        }
    }

    private void replaceViewEx(Spanned spannedStr){
        for (int i = 0; i < spans.length; i++) {
            EmptySpan s = spans[i];
            int start = spannedStr.getSpanStart(s);
            Layout layout = tv.getLayout();
            int line = layout.getLineForOffset(start);
            int topPadding = tv.getCompoundPaddingTop();
            int leftMargin = (int) layout.getPrimaryHorizontal(start);
            int descent = layout.getLineDescent(line);
            int base = layout.getLineBaseline(line);
            int spanTop = base + descent - s.height();
            int topMargin = spanTop + topPadding;

            RelativeLayout.LayoutParams params;
            View view  = hashMap.get(s);
            if(view == null){
                EditText myView = new EditText(rlMaskView.getContext());
                myView.setSingleLine();
                myView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                myView.setText("2222");
                params = new RelativeLayout.LayoutParams(s.width(), s.height());
                params.leftMargin = leftMargin;
                params.topMargin = topMargin;
                rlMaskView.addView(myView, params);
                hashMap.put(s,myView);
            }else {
                params = (RelativeLayout.LayoutParams) view.getLayoutParams();
                params.leftMargin = leftMargin;
                params.topMargin = topMargin;
                view.setLayoutParams(params);
            }
        }
    }
}
