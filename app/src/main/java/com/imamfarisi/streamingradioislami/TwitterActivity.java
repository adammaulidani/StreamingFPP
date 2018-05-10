package com.imamfarisi.streamingradioislami;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TwitterActivity extends AppCompatActivity {

    private WebView webV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        webV = (WebView)findViewById(R.id.webV);
        String url = "https://twitter.com/sbs_fm_fppundip";
        webV.getSettings().setJavaScriptEnabled(true);
        webV.setFocusable(true);
        webV.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webV.getSettings().setDomStorageEnabled(true);
        webV.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webV.getSettings().setDatabaseEnabled(true);
        webV.getSettings().setAppCacheEnabled(true);
        webV.loadUrl(url);
        webV.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (webV.canGoBack()){
            webV.goBack();
        }else{
            super.onBackPressed();
        }
    }
}

