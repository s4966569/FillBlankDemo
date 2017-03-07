package com.example.sp.fillblankdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by sp on 17-3-6.
 */

public class ActivityB extends Activity {
    RelativeLayout relativeLayout;
    ImageView imageView;
    TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_root);
        imageView = (ImageView) findViewById(R.id.iv_bottom);
        tv = (TextView) findViewById(R.id.tv);
        String str = FileUtils.fetchFileContent(this, "html.txt");
        HtmlImageGetter htmlImageGetter = new HtmlImageGetter(tv.getContext(), tv);
        MyImageGetter myImageGetter = new MyImageGetter(this, tv);
        Spanned spannedStr = Html.fromHtml(str, htmlImageGetter, new MyTagHandler());
        tv.setText(spannedStr);
        Glide.with(this)
                .load("http://scc.jsyxw.cn/image/20160812/1470975461952608.png")
                .into(imageView);
        for(int i=0; i < 3;i++){
            EditText editText = new EditText(this);
            editText.setText("1111");
            editText.setSingleLine();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150,120);
            layoutParams.topMargin = i * 120;
            editText.setLayoutParams(layoutParams);
            relativeLayout.addView(editText);
        }
    }
}
