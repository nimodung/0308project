package com.example.vote2;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends Activity {

    Button btnFinish;
    TextView tvFamousName;
    ImageView imgFamous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultactivity);

        btnFinish = (Button)findViewById(R.id.btn_finish);
        tvFamousName = (TextView)findViewById(R.id.tv_famousname);
        imgFamous = (ImageView)findViewById(R.id.img_famous);

        Intent intent = getIntent();
        String imageName[] = intent.getStringArrayExtra("ImageName");
        int[] voteCount = intent.getIntArrayExtra("VoteResult");
        int imageID[] = {R.mipmap.img1, R.mipmap.img2, R.mipmap.img3, R.mipmap.img4, R.mipmap.img5, R.mipmap.img6,
                R.mipmap.img7, R.mipmap.img8, R.mipmap.img9};

        TextView textView[] = new TextView[9];


        RatingBar ratingBar[] = new RatingBar[9];

        Integer tvID[] = {R.id.tv_name1, R.id.tv_name2, R.id.tv_name3, R.id.tv_name4, R.id.tv_name5, R.id.tv_name6, R.id.tv_name7, R.id.tv_name8, R.id.tv_name9};
        Integer rbarID[] = {R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9};

        int max_vote = 0, max_index = 0;
        for(int i = 0; i < 9; i++) {
            if(voteCount[i] > max_vote) {
                max_vote = voteCount[i];
                max_index = i;
            }

            textView[i] = (TextView)findViewById(tvID[i]);
            textView[i].setText(imageName[i]) ;
            ratingBar[i] = (RatingBar)findViewById(rbarID[i]);
            ratingBar[i].setRating((float) voteCount[i]);
        }
        if(max_vote != 0) {
            tvFamousName.setText(imageName[max_index]);
            imgFamous.setImageResource(imageID[max_index]);
        }

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
