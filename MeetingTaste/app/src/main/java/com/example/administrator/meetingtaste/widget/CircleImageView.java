package com.example.administrator.meetingtaste.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.example.administrator.meetingtaste.R;

public class CircleImageView extends de.hdodenhof.circleimageview.CircleImageView {
    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (pressed) {
            setBorderColor(getResources().getColor(R.color.colorAccent));
        } else {
            setBorderColor(Color.WHITE);
        }
    }
}
