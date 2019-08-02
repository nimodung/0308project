package com.example.vote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button BtnVote;
    ImageView ImgView[];
    int voteCount[] = new int[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < 9; i++) {
            voteCount[i] = 0;
        }
        BtnVote = (Button)findViewById(R.id.btn_vote);

        Integer Image_ID[] = {R.id.img1, R.id.img2, R.id.img3, R.id.img4, R.id.img5, R.id.img6, R.id.img7, R.id.img8, R.id.img9};

        final String imgName[] = {
                "혀내밀고 자는 모모", "자고있는 모모랑 둥둥이", "암모나이트 모모 둥둥",
                "길다란 둥둥이", "가방 산 둥둥이", "드러누운 둥둥이",
                "생각하는 모모", "사이좋은 모모랑 둥둥이", "암모나이트 둥둥"
        };

        ImgView = new ImageView[9];
        for(int i = 0; i < 9; i++) {
            final int index;
            index = i;
            ImgView[i] = (ImageView)findViewById(Image_ID[i]);
            ImgView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(), imgName[index] + " : " + voteCount[index] + "표", Toast.LENGTH_SHORT).show();
                }
            });
        }

        BtnVote = (Button)findViewById(R.id.btn_vote);
        BtnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("ImageName",imgName);
                intent.putExtra("VoteResult", voteCount);
                startActivity(intent);
            }
        });
    }


}
