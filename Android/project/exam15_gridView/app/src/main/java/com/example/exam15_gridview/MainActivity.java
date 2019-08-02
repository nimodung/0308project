package com.example.exam15_gridview;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridCard = (GridView)findViewById(R.id.gridview_card);

        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gridCard.setAdapter(gAdapter);
    }

    public class MyGridAdapter extends BaseAdapter {
       Context context;
       public MyGridAdapter(Context c) {
           context = c;
       }

       Integer cardImageID[] = {R.mipmap.card_front_black, R.mipmap.card_front_blue, R.mipmap.card_front_red, R.mipmap.card_level1_1,
                                R.mipmap.card_level1_2, R.mipmap.card_level1_3, R.mipmap.card_level1_4, R.mipmap.card_level1_5,
                                 R.mipmap.card_level1_6, R.mipmap.card_level1_7, R.mipmap.card_level1_8, R.mipmap.card_level2_1,
                                R.mipmap.card_level2_2, R.mipmap.card_level2_3, R.mipmap.card_level2_4, R.mipmap.card_level2_5,
                                R.mipmap.card_level2_6, R.mipmap.card_level2_7, R.mipmap.card_level2_8, R.mipmap.ha_su,
                                R.mipmap.irin, R.mipmap.iu, R.mipmap.sola, R.mipmap.teayoen};

        @Override
        public int getCount() {
            return cardImageID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);
            imageView.setImageResource(cardImageID[position]);

            final int pos = position;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dialogView = (View)View.inflate(MainActivity.this, R.layout.dialog, null);

                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                    ImageView ivCard = (ImageView)dialogView.findViewById(R.id.img_ty);
                    ivCard.setImageResource(cardImageID[pos]);

                    dlg.setTitle("카드 이미지");
                    dlg.setIcon(R.mipmap.ic_launcher);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기",null);
                    dlg.show();
                }
            });



            return imageView;
        }

    }
}
