package com.example.sp.fillblankdemo;

import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;

import org.xml.sax.XMLReader;

import java.util.List;
import java.util.Random;

/**
 * Created by sp on 17-2-24.
 */

public abstract class ReplaceTagHandler implements Html.TagHandler {
    protected int start,end;
    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

        if (opening && tag.toLowerCase().equals(getTag())) {
            start = output.length();
        }
        if (!opening && tag.toLowerCase().equals(getTag())) {
            end = output.length();
            if (start != end) {
                EmptySpan es;
                try {
                    es = getSpanType().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    return;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return;
                }
                Random r = new Random();
                es.width = r.nextInt(100) + 100; // 100 - 200之间
                output.setSpan(es, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    protected abstract Class<? extends EmptySpan> getSpanType();

    protected abstract String getTag();
}
