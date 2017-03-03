package com.example.sp.fillblankdemo;

import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;

import org.xml.sax.XMLReader;

import java.util.Random;

/**
 * Created by cailei on 05/01/2017.
 */

public class MyTagHandler implements Html.TagHandler {
    int start,end;
    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

        // FillBlank处理
        if (opening && tag.toLowerCase().equals("fillblank")) {
            start = output.length();
        }

        if (!opening && tag.toLowerCase().equals("fillblank")) {
            end = output.length();
            if (start != end) {
                output.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        // 纯空处理
        if (opening && tag.toLowerCase().equals("empty")) {
           start = output.length();
        }
        if (!opening && tag.toLowerCase().equals("empty")) {
            end = output.length();
            if (start != end) {
                EmptySpan es = new FillBlankEmptySpan();
                output.setSpan(es, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }
}
