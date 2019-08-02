package com.example.exam11_intent;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editNumber1, editNumber2;
    Button btnAdd;
    RadioButton RbtnAdd, RbtnSub, RbtnMul, RbtnDiv;
    RadioGroup RgroupOP;
    final int add = 0, sub = 1, mul = 2, div =3;
    int operator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber1 = (EditText)findViewById(R.id.edit_number1);
        editNumber2 = (EditText)findViewById(R.id.edit_number2);

        RgroupOP = (RadioGroup)findViewById(R.id.radiogroup);
        RbtnAdd = (RadioButton)findViewById(R.id.rbtn_add);
        RbtnSub = (RadioButton)findViewById(R.id.rbtn_sub);
        RbtnMul = (RadioButton)findViewById(R.id.rbtn_mul);
        RbtnDiv = (RadioButton)findViewById(R.id.rbtn_div);

        btnAdd = (Button)findViewById(R.id.btn_add);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(RgroupOP.getCheckedRadioButtonId() == R.id.RbtnAdd) opcode = "add";
                if(RbtnAdd.isChecked()) {
                    operator = add;
                }
                else if(RbtnSub.isChecked()) {
                    operator = sub;
                }
                else if(RbtnMul.isChecked()) {
                    operator = mul;
                }
                else if(RbtnDiv.isChecked()) {
                    operator = div;
                }
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("Num1", Integer.parseInt(editNumber1.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(editNumber2.getText().toString()));
                intent.putExtra("Operator", operator);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            int operator = data.getIntExtra("Operator", 0);
            int result = data.getIntExtra("Result", 0);
            if(operator == 0) {

                Toast.makeText(getApplicationContext(), "더하기 결과 : " + result, Toast.LENGTH_SHORT).show();
            }
            else if(operator == 1) {
                Toast.makeText(getApplicationContext(), "빼기 결과 : " + result, Toast.LENGTH_SHORT).show();
            }
            else if(operator == 2) {
                Toast.makeText(getApplicationContext(), "곱하기 결과 : " + result, Toast.LENGTH_SHORT).show();
            }
            else if(operator == 3) {
                Toast.makeText(getApplicationContext(), "나누기 결과 : " + result, Toast.LENGTH_SHORT).show();
            }
        }
        else Toast.makeText(getApplicationContext(), "infinity", Toast.LENGTH_SHORT).show();
    }
}
