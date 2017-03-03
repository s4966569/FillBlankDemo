package com.example.sp.fillblankdemo;

/**
 * Created by sp on 17-2-24.
 */

public class FillBlankTagHandler extends ReplaceTagHandler {
    @Override
    protected Class<? extends EmptySpan> getSpanType() {
        return FillBlankEmptySpan.class;
    }

    @Override
    protected String getTag() {
        return "empty";
    }
}
