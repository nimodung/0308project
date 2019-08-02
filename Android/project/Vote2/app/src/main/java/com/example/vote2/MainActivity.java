package com.example.vote2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView[];
    Button btnVote;
    int voteCount[] = new int[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < 9; i++) {
            voteCount[i] = 0;
        }

        final String imageName[] = {"잠자는 모모", "사이좋은 모모랑 둥둥이", "암모나이트 모모, 둥둥",
                            "길다란 둥둥이", "가방 산 둥둥이", "드러누운 둥둥이",
                            "생각하는 모모", "사이좋은 둥둥이랑 모모", "암모나이트 둥둥"};
        final Integer imageID[] = {R.id.img1, R.id.img2, R.id.img3, R .id.img4, R.id.img5, R.id.img6, R.id.img7, R.id.img8, R.id.img9};
        btnVote = (Button)findViewById(R.id.btn_vote);
        imageView = new ImageView[9];

        for(int i = 0; i < 9; i++) {
            final int index = i;
            imageView[i] = (ImageView)findViewById(imageID[i]);
            imageView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(),
                            imageName[index] + " : " + voteCount[index] + "표", Toast.LENGTH_SHORT).show();
                }
            });
        }

        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("ImageName", imageName);
                intent.putExtra("VoteResult", voteCount);

                startActivity(intent);
            }
        });
    }


}
