package com.iavariav.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "debug";
    private AdView adView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // TODO inisialisasi MobileAds
        MobileAds.initialize(MainActivity.this, initializationStatus -> {
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        // banner
//        AdView adViews = new AdView(this);
//        adViews.setAdSize(AdSize.LARGE_BANNER);
//        adViews.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        // TODO call showAdsBanner
        showAdsBanner(adRequest);

        // TODO definisi InterstitialAd
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.d(TAG, "onAdLoaded" + mInterstitialAd);
                showAdsInterstitial();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.d(TAG, loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });



    }

    // TODO showAdsBanner
    private void showAdsBanner(AdRequest adRequest) {
        adView.loadAd(adRequest);
    }

    // TODO method showAdsInterstitial
    private void showAdsInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(MainActivity.this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed.
                    Log.d("TAG", "The ad was dismissed.");
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when fullscreen content failed to show.
                    Log.d("TAG", "The ad failed to show.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;
                    Log.d("TAG", "The ad was shown.");
                }
            });
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }

    private void initView() {
        adView = findViewById(R.id.adView);
    }
}