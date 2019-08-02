package com.example.exam02_idolviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.nio.InvalidMarkException;

public class MainActivity extends AppCompatActivity {

    Button Btn_iu, Btn_teayeon, Btn_sola, Btn_jennie;
    ImageView Img_iu, Img_teayeon, Img_sola, Img_jennie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn_iu = (Button)findViewById(R.id.btn_iu);
        Btn_teayeon = (Button)findViewById(R.id.btn_teayeon);
        Btn_sola = (Button)findViewById(R.id.btn_sola);
        Btn_jennie = (Button)findViewById(R.id.btn_jennie);

        Img_iu = (ImageView)findViewById(R.id.img_i);
        Img_teayeon = (ImageView)findViewById(R.id.img_t);
        Img_sola = (ImageView)findViewById(R.id.img_s);
        Img_jennie = (ImageView)findViewById(R.id.img_j);

        Btn_iu.setOnClickListener(mOnclickListener);
        Btn_teayeon.setOnClickListener(mOnclickListener);
        Btn_sola.setOnClickListener(mOnclickListener);
        Btn_jennie.setOnClickListener(mOnclickListener);
    }
    Button.OnClickListener mOnclickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Img_iu.setVisibility(View.INVISIBLE);
            Img_teayeon.setVisibility(View.INVISIBLE);
            Img_sola.setVisibility(View.INVISIBLE);
            Img_jennie.setVisibility(View.INVISIBLE);

            switch (v.getId())
            {
                case R.id.btn_iu :
                    Img_iu.setVisibility(View.VISIBLE);
                    break;
                case R.id.btn_teayeon :
                    Img_teayeon.setVisibility(View.VISIBLE);
                    break;
                case R.id.btn_sola :
                    Img_sola.setVisibility(View.VISIBLE);
                    break;
                case R.id.btn_jennie :
                    Img_jennie.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
}
