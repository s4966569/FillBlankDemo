package com.example.sp.fillblankdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by sp on 17-2-24.
 */

public class FillBlankTextView extends ReplaceableTextView {
    public FillBlankTextView(Context context) {
        super(context);
    }

    public FillBlankTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FillBlankTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected View getReplaceView() {
        Button button = new Button(getContext());
        button.setText("33333");
        return button;
    }

    @Override
    protected ReplaceTagHandler getTagHandler() {
        return new FillBlankTagHandler();
    }
}
