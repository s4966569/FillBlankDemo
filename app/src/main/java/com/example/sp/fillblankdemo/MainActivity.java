package com.example.sp.fillblankdemo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    private MyTextView textView;
    private RelativeLayout rlMaskView;
    private ImageView bottomImageView;
    private FrameLayout fl_content;
    Spanned spannedStr;
    EmptySpan[] spans;
    private Button btn_jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (MyTextView) findViewById(R.id.tv_top);
        btn_jump = (Button) findViewById(R.id.btn_jump);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        String str = FileUtils.fetchFileContent(this,"html.txt");

        HtmlImageGetter htmlImageGetter = new HtmlImageGetter(textView.getContext(), textView);
        MyImageGetter myImageGetter = new MyImageGetter(this,textView);
        spannedStr = Html.fromHtml(str, htmlImageGetter, new MyTagHandler());
        spans = spannedStr.getSpans(0, spannedStr.length(), EmptySpan.class);
        for (EmptySpan s : spans) {
            s.lineHeight = textView.getLineHeight();
        }
        textView.setText(spannedStr, TextView.BufferType.SPANNABLE);
        rlMaskView = (RelativeLayout) findViewById(R.id.rl_top_mask);
        bottomImageView = (ImageView) findViewById(R.id.iv_bottom);


        Glide.with(this)
                .load("http://scc.jsyxw.cn/image/20160812/1470975461952608.png")
                .into(bottomImageView);
        bottomImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityA.class);
                startActivity(intent);
            }
        });

//        textView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                if(left != oldLeft || right != oldRight || top!= oldTop || bottom != oldBottom){
//                    bottomImageView.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            replaceSpanWithView(spannedStr);
//                        }
//                    });
//                }
//            }
//        });

        textView.setOnDrawFinishedListener(new MyTextView.OnDrawFinishedListener() {
            @Override
            public void onDrawFinished() {
                replaceView(spannedStr);
            }
        });

    }

    private void replaceView(Spanned spannedStr) {
        if(rlMaskView.getChildCount() >0){
            rlMaskView.removeAllViews();
        }
        for (EmptySpan s : spans) {

            int start = spannedStr.getSpanStart(s);

            Layout layout = textView.getLayout();

            int line = layout.getLineForOffset(start);

            int topPadding = textView.getCompoundPaddingTop();

            int leftMargin = (int) layout.getPrimaryHorizontal(start);

            int descent = layout.getLineDescent(line);

            int base = layout.getLineBaseline(line);

            int spanTop = base + descent - s.height();

            int topMargin = spanTop + topPadding;

            View myView = new View(rlMaskView.getContext());

            myView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(s.width(), s.height());

            params.leftMargin = leftMargin;

            params.topMargin = topMargin;

            rlMaskView.addView(myView, params);
        }
    }
}
