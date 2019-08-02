package com.example.exam07_basic_view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    RadioButton Rdo_date, Rdo_time;
    CalendarView cal_view;
    TimePicker tPicker;
    Button Btn_ok;
    TextView Year, Month, Date, Hour, Min;

    int select_year, select_month, select_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rdo_date = (RadioButton)findViewById(R.id.id_date);
        Rdo_time = (RadioButton)findViewById(R.id.id_time);

        cal_view = (CalendarView)findViewById(R.id.date_picker);
        tPicker = (TimePicker)findViewById(R.id.time_picker);

        Btn_ok = (Button)findViewById(R.id.btn_ok);

        Year = (TextView)findViewById(R.id.tv_year);
        Month = (TextView)findViewById(R.id.tv_month);
        Date = (TextView)findViewById(R.id.tv_date);
        Hour = (TextView)findViewById(R.id.tv_hour);
        Min = (TextView)findViewById(R.id.tv_min);

        Rdo_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tPicker.setVisibility(View.INVISIBLE);
                cal_view.setVisibility(View.VISIBLE);
            }
        });

        Rdo_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal_view.setVisibility(View.INVISIBLE);
                tPicker.setVisibility(View.VISIBLE);


            }
        });

        cal_view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                select_year = year;
                select_month = month + 1; //month 0부터 시작
                select_date = dayOfMonth;
            }
        });

        Btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Year.setText(Integer.toString(select_year));
                Month.setText(Integer.toString(select_month));
                Date.setText(Integer.toString(select_date));
                Hour.setText(Integer.toString(tPicker.getCurrentHour()));
                Min.setText(Integer.toString(tPicker.getCurrentMinute()));
            }
        });
    }
}
