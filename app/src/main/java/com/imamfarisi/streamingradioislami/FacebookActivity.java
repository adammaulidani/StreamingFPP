package com.imamfarisi.streamingradioislami;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FacebookActivity extends AppCompatActivity {

    private WebView webVFb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        webVFb = (WebView)findViewById(R.id.web_Vfb);
        String url = "https://www.facebook.com/RadioSBS/";
        webVFb.getSettings().setJavaScriptEnabled(true);
        webVFb.setFocusable(true);
        webVFb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webVFb.getSettings().setDomStorageEnabled(true);
        webVFb.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webVFb.getSettings().setDatabaseEnabled(true);
        webVFb.getSettings().setAppCacheEnabled(true);
        webVFb.loadUrl(url);
        webVFb.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (webVFb.canGoBack()){
            webVFb.goBack();
        }else{
            super.onBackPressed();
        }
    }
}