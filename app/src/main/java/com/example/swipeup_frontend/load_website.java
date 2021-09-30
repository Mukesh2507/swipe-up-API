package com.example.swipeup_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class load_website extends AppCompatActivity {


    WebView ourbrowser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_viewer);

        ourbrowser = (WebView) findViewById(R.id.webviewer);
        System.out.println("Webview");
        ourbrowser.setWebViewClient(new Mybrowser());
        ourbrowser.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();
        String urll = intent.getStringExtra("urll");
        System.out.println(urll);
        System.out.println("****");
        ourbrowser.loadUrl(urll);

    }

    private class Mybrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String request) {
            view.loadUrl(request);
            return true;
        }
    }
}
