package com.example.exam10_activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    Button btnNew;
    RadioButton rbtn_Second, rbtn_Third;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNew = (Button)findViewById(R.id.btn_new);
        rbtn_Second = (RadioButton)findViewById(R.id.rbtn_second);
        rbtn_Third = (RadioButton)findViewById(R.id.rbtn_third);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbtn_Second.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(intent);
                }
                else if(rbtn_Third.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
                    startActivity(intent);
                }
            }
        });


    }


}
