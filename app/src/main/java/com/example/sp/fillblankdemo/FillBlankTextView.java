package com.example.sp.fillblankdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by sp on 17-2-24.
 */

public class FillBlankTextView extends SpanReplaceableTextView<EditText> {
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
    protected EditText getReplaceView() {
        EditText editText = new EditText(getContext());
        editText.setText("11111");
        editText.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        editText.setSingleLine();
        return editText;
    }

    @Override
    protected ReplaceTagHandler getTagHandler() {
        return new FillBlankTagHandler();
    }
}
