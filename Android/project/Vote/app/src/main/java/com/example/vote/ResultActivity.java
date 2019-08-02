package com.example.vote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends Activity {

    Button BtnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        Intent intent = getIntent();
        String[] imageName = intent.getStringArrayExtra("ImageName");
        int[] voteCount = intent.getIntArrayExtra("VoteResult");

        TextView textView[]  = new TextView[9];
        RatingBar ratingBar[] = new RatingBar[9];

        Integer tvID[] = {R.id.tv_name1, R.id.tv_name2, R.id.tv_name3, R.id.tv_name4, R.id.tv_name5, R.id.tv_name6, R.id.tv_name7, R.id.tv_name8, R.id.tv_name9};
        Integer rbarID[] = {R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9};

        for(int i = 0; i < 9; i++) {
            textView[i] = (TextView)findViewById(tvID[i]);
            textView[i].setText(imageName[i]) ;
            ratingBar[i] = (RatingBar)findViewById(rbarID[i]);
            ratingBar[i].setRating((float) voteCount[i]);
        }

        BtnFinish = (Button)findViewById(R.id.btn_finish);
        BtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
