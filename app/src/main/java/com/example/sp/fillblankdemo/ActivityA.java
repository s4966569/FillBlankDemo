package com.example.sp.fillblankdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by sp on 17-2-24.
 */

public class ActivityA extends Activity {
    private FillBlankTextView fbTextView;
    private Button btn_remove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        fbTextView = (FillBlankTextView) findViewById(R.id.fbTextView);
        btn_remove = (Button) findViewById(R.id.btn_remove);

        fbTextView.setText(FileUtils.fetchFileContent(this,"html.txt"));

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbTextView.removeAllReplacementView();
            }
        });
    }
}
