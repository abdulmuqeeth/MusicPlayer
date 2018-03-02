package com.abdulmuqeethmohammed.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VideoScreen extends AppCompatActivity {

    private WebView webView;
    private String videoUrl;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_screen);

        Intent receivedIntent = getIntent();
        videoUrl = receivedIntent.getStringExtra(getString(R.string.url_tag));

        webView = (WebView) findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient());

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webView.loadUrl(videoUrl);
    }

    /*
    * This Method is called when the back button is pressed on the device
    * Stops the webView content from playing after backbutton is pressed
    * Finishes the current activity
    */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        webView.reload();
        finish();
    }
}
