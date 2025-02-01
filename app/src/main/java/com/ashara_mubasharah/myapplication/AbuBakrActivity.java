package com.ashara_mubasharah.myapplication;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class AbuBakrActivity extends BaseActivity {

    TextView textView2;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abu_bakr);

        // `setupToolbar` মেথড কল করে টাইটেল এবং ব্যাক বাটন সেট করা
        setupToolbar("সংক্ষিপ্ত জীবনী", true);


        textView2 = findViewById(R.id.txt2Id);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            textView2.setText(Html.fromHtml(getString(R.string.abu_bakar_info),Html.FROM_HTML_MODE_LEGACY));

        }else {

            textView2.setText(Html.fromHtml(getString(R.string.abu_bakar_info)));

        }


    }
}
