package com.example.sp.fillblankdemo;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by sp on 17-3-3.
 */

public class FillBlankEmptySpan extends EmptySpan {

    @Override
    protected int width() {
        return 200;
    }

    @Override
    protected int height() {
        return lineHeight;
    }

    @Override
    protected int color() {
        return Color.TRANSPARENT;
    }
}
