package com.example.exam12_implicatintent;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button BtnDial, BtnWebsite, BtnMap, BtnSearch, BtnSMS, BtnPhoto, BtnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //주로 초기화
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.util.Log.d("life", "onCreate IN");

        BtnDial = (Button)findViewById(R.id.btn_dial);
        BtnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:010-1234-5678");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                android.util.Log.d("life", "BtnDial onClickListener");
//                android.util.Log.i("액티비티 테스트", "hihi");
            }
        });

        BtnWebsite = (Button)findViewById(R.id.btn_website);
        BtnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.naver.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        BtnMap = (Button)findViewById(R.id.btn_map) ;
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://maps.google.com/maps?q=" + 37.4906831 + "," + 126.7231096 );
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        BtnSearch = (Button)findViewById(R.id.btn_search);
        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "다우니 보라색");
                startActivity(intent);
            }
        });

        BtnSMS = (Button)findViewById(R.id.btn_sms);
        BtnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body", "안녕하세요?");
                intent.setData(Uri.parse("smsto:" + Uri.encode("010-1234-4567")));
                startActivity(intent);
            }
        });

        BtnFinish = (Button)findViewById(R.id.btn_finish);
        BtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        android.util.Log.d("life", "onStart()");
        //  Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        android.util.Log.d("life","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        android.util.Log.d("life","onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        android.util.Log.d("life","onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        android.util.Log.d("life","onRestart()");
    }

    @Override
    protected void onDestroy() { //종료하기 직전에 자원들 반납해야하는 것들은 destroy에서
        super.onDestroy();
        android.util.Log.d("life","onDestroy()");
    }

}
