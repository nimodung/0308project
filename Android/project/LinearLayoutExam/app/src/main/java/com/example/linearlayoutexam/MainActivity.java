package com.example.linearlayoutexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioGroup RG_Orientation, RG_Gravity;
    RadioButton Rbtn_horizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RG_Orientation = (RadioGroup)findViewById(R.id.rgroup_orientation);
        RG_Orientation.setOnCheckedChangeListener(this);
        RG_Gravity = (RadioGroup)findViewById(R.id.rgroup_gravity);
        RG_Gravity.setOnCheckedChangeListener(this);
        Rbtn_horizontal = (RadioButton)findViewById(R.id.rbtn_horizontal);
        Rbtn_horizontal.setTextColor(0xff00ffff);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case R.id.rbtn_horizontal :
                RG_Orientation.setOrientation(LinearLayout.HORIZONTAL);
                break;
            case R.id.rbtn_vertical :
                RG_Orientation.setOrientation(LinearLayout.VERTICAL);
                break;
            case R.id.rbtn_left :
                RG_Gravity.setGravity(Gravity.LEFT);
                break;
            case R.id.rbtn_center :
                RG_Gravity.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            case R.id.rbtn_right :
                RG_Gravity.setGravity(Gravity.RIGHT);
                break;
        }
    }
}
