package com.autoads.app.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.ViewGroup;
import android.view.Window;

import com.autoads.app.R;


public class MyDialog extends Dialog {

    public AppCompatTextView msgTv;

    public MyDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.pop_up_progress);
        msgTv = (AppCompatTextView) findViewById(R.id.msg_tv);
    }

    public void setMessage(String message) {
        if (!message.isEmpty()) {
            msgTv.setText(message + "");
        }
    }

//    @Override
//    public void show() {
//        super.show();
//    }
//
//    @Override
//    public void dismiss() {
//        super.dismiss();
//    }
}
