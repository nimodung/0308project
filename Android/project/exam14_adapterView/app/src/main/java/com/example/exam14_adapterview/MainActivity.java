package com.example.exam14_adapterview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] mid = {"재성", "우혁", "은주", "지훈",
                               "종하", "대희", "영숙", "춘우",
                                "재형", "경윤", "모모", "둥둥",
                                "뽀야", "진진" ,"콩자", "은자"};
        final ArrayList<String> nameList = new ArrayList<String>();

        final EditText edName = (EditText)findViewById(R.id.ed_name);
        Button btnName = (Button)findViewById(R.id.btn_name);
        final ListView listView = (ListView)findViewById(R.id.list);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, nameList);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //position : index of array
                Toast.makeText(getApplicationContext(), nameList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameList.add(edName.getText().toString());
                adapter.notifyDataSetChanged(); //바뀐 리스트를 adapter에 적용
                edName.setText("");
            }
        });
    }
}
