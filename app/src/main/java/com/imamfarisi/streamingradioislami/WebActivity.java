package com.imamfarisi.streamingradioislami;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    private WebView wView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wView = (WebView)findViewById(R.id.webV);
        String url = "http://fpp.undip.ac.id/radio/";
        wView.getSettings().setJavaScriptEnabled(true);
        wView.setFocusable(true);
        wView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wView.getSettings().setDomStorageEnabled(true);
        wView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        wView.getSettings().setDatabaseEnabled(true);
        wView.getSettings().setAppCacheEnabled(true);
        wView.loadUrl(url);
        wView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (wView.canGoBack()){
            wView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}