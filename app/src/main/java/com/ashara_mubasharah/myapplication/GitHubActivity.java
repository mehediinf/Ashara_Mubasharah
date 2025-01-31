package com.ashara_mubasharah.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class GitHubActivity extends BaseActivity {

    private WebView webView;
    private ProgressBar progressBar;

    @SuppressLint({"SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub);

        // টুলবার সেটআপ
        setupToolbar("About Us",true);

        // UI এলিমেন্ট খুঁজে বের করা
        webView = findViewById(R.id.webViewId);
        progressBar = findViewById(R.id.progressBarId);

        // WebView সেটআপ
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // JavaScript চালু করা
        webSettings.setDomStorageEnabled(true); // স্টোরেজ চালু করা

        // WebViewClient সেট করা, যাতে ব্রাউজার না খোলে

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

        webView.loadUrl("https://github.com/mehediinf");

        // ব্যাক বাটন হ্যান্ডল করা (OnBackPressedDispatcher)
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()){
                    webView.goBack();
                }
                else {
                    finish();
                }
            }
        });

    }


}
