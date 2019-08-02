package com.example.exam05_layout_colorchange;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import static com.example.exam05_layout_colorchange.R.*;

public class MainActivity extends AppCompatActivity {


    LinearLayout l_bottom, top_left_top, top_left_bottom, top_right_left, top_right_center, top_right_right;
    Color color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l_bottom = (LinearLayout) findViewById(id.l_bottom);
        top_left_top = (LinearLayout) findViewById(id.top_left_top);
        top_left_bottom = (LinearLayout) findViewById(id.top_left_bottom);
        top_right_left = (LinearLayout) findViewById(id.top_right_left);
        top_right_center = (LinearLayout) findViewById(id.top_right_center);
        top_right_right = (LinearLayout) findViewById(id.top_right_right);

        l_bottom.setOnClickListener(mOnclickLisenter);
        top_left_top.setOnClickListener(mOnclickLisenter);
        top_left_bottom.setOnClickListener(mOnclickLisenter);
        top_right_left.setOnClickListener(mOnclickLisenter);
        top_right_center.setOnClickListener(mOnclickLisenter);
        top_right_right.setOnClickListener(mOnclickLisenter);
    }

    View.OnClickListener mOnclickLisenter = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //ColorDrawable viewColor;
            //int colorId;
            switch (v.getId()) {
                case id.l_bottom:
                    //viewColor = (ColorDrawable)top_left_bottom.getBackground();
                    //colorId = viewColor.getColor();
                    l_bottom.setBackground(top_left_bottom.getBackground());
                    break;
                case id.top_left_top:
                    //viewColor = (ColorDrawable)top_right_left.getBackground();
                    //colorId = viewColor.getColor();
                    top_left_top.setBackground(top_right_left.getBackground());
                    break;
                case id.top_left_bottom:
                    // viewColor = (ColorDrawable)top_left_top.getBackground();
                    //colorId = viewColor.getColor();
                    top_left_bottom.setBackground(top_left_top.getBackground());
                    break;
                case id.top_right_left:
                    // viewColor = (ColorDrawable)top_right_center.getBackground();
                    // colorId = viewColor.getColor();
                    top_right_left.setBackground(top_right_center.getBackground());
                    break;
                case id.top_right_center:
                    //viewColor = (ColorDrawable)top_right_right.getBackground();
                    //colorId = viewColor.getColor();
                    //top_right_center.setBackgroundColor(colorId);
                    top_right_center.setBackground(top_right_right.getBackground());
                    break;
                case id.top_right_right:
                    // viewColor = (ColorDrawable)l_bottom.getBackground();
                    // colorId = viewColor.getColor();
                    top_right_right.setBackground(l_bottom.getBackground());
                    break;
            }
        }
    };
}




