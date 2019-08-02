package com.example.exam11_intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends Activity {
    int ResultValue;
    Button btnBack;
    final int add = 0, sub = 1, mul = 2, div = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        Intent inIntent = getIntent();

        final boolean isZeroDivide;
        final int operator = inIntent.getIntExtra("Operator", 0);
        if(operator == add)
        {
            isZeroDivide = false;
            ResultValue = inIntent.getIntExtra("Num1", 0) + inIntent.getIntExtra("Num2", 0);
        }
        else if(operator == sub)  {
            isZeroDivide = false;
            ResultValue = inIntent.getIntExtra("Num1", 0) - inIntent.getIntExtra("Num2", 0);
        }
        else if(operator == mul)  {
            isZeroDivide = false;
            ResultValue = inIntent.getIntExtra("Num1", 0) * inIntent.getIntExtra("Num2", 0);
        }
        else if(operator == div) {
            if (inIntent.getIntExtra("Num2", 0) == 0) {
                isZeroDivide = true;
                ResultValue = 0;
            } else {
                isZeroDivide = false;
                ResultValue = inIntent.getIntExtra("Num1", 0) / inIntent.getIntExtra("Num2", 0);
            }
        }

        btnBack = (Button)findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("Result", ResultValue);
                outIntent.putExtra("Operator", operator);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });

    }
}
