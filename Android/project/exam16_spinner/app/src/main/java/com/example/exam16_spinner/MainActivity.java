package com.example.exam16_spinner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] imageName = {"흰토끼", "노란오리", "하얀둥근애",
                "노란머리", "갈색곰", "라이언", "무지",
                "어피치", "색깔조커","흑백조커"};
        final Integer[] imageID = {R.mipmap.card_level1_1, R.mipmap.card_level1_2, R.mipmap.card_level1_3,
                R.mipmap.card_level1_4, R.mipmap.card_level1_5, R.mipmap.card_level1_6,
                R.mipmap.card_level1_7, R.mipmap.card_level1_8, R.mipmap.card_level2_1, R.mipmap.card_level2_2 };


        final ImageView imageView = (ImageView)findViewById(R.id.img_poster);
        Button btnDialog = (Button)findViewById(R.id.btn_dialog);

        //Spinner mSpinner = (Spinner)findViewById(R.id.spinner);


        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialogView = (View)View.inflate(MainActivity.this, R.layout.dialog, null);
                final AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                ListView listView = (ListView)dialogView.findViewById(R.id.listview);
                Button btnOK = (Button)dialogView.findViewById(R.id.btn_ok);
                Button btnClose = (Button)dialogView.findViewById(R.id.btn_close);
                //adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, imageName);
              // MyAdapter adapter = new MyAdapter(MainActivity.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_single_choice, imageName);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        imageView.setImageResource(imageID[position]);
                    }
                });


                dlg.setTitle("이미지 리스트");
                dlg.setView(dialogView);
                dlg.show();



            }

        });
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, imageName);

      //  mSpinner.setAdapter(adapter);

    /*  mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
               imageView.setImageResource(imageID[position]);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        */
    }


}
