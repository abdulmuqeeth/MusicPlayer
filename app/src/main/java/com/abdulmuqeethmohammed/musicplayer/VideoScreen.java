package com.abdulmuqeethmohammed.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VideoScreen extends AppCompatActivity {

    private WebView webView;
    private WebChromeClient webChromeClient;
    private String videoUrl;
    private WebSettings webSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_screen);

        Intent receivedIntent = getIntent();
        videoUrl = receivedIntent.getStringExtra(getString(R.string.url_tag));

        webView = (WebView) findViewById(R.id.webView1);

        webChromeClient = new WebChromeClient();
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(new WebViewClient());

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webView.loadUrl(videoUrl);
    }
}
