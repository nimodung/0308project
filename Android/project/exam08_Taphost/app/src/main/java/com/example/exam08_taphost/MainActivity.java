package com.example.exam08_taphost;

import android.app.ActionBar;
import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("TAG1").setIndicator("둥둥이");
        tabSpec1.setContent(R.id.img_dungdi);
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("TAG2").setIndicator("솔라");
        tabSpec2.setContent(R.id.img_sola);
        tabHost.addTab(tabSpec2);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("TAG3").setIndicator("아이유");
        tabSpec3.setContent(R.id.img_iu);
        tabHost.addTab(tabSpec3);

        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("TAG4").setIndicator("태연");
        tabSpec4.setContent(R.id.img_teayeon);
        tabHost.addTab(tabSpec4);

        tabHost.setCurrentTab(0);
    }
}
