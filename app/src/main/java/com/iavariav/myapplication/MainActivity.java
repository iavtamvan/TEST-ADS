package com.iavariav.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        MobileAds.initialize(MainActivity.this, initializationStatus -> {
            Toast.makeText(this, "" + initializationStatus.getAdapterStatusMap(), Toast.LENGTH_SHORT).show();
        });

//        AdView adViews = new AdView(this);
//        adViews.setAdSize(AdSize.LARGE_BANNER);
//        adViews.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void initView() {
        adView = findViewById(R.id.adView);
    }
}