package com.ashara_mubasharah.myapplication;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Umar_ibn_al_khattabActivity extends BaseActivity {


    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umar_ibn_al_khattab);

        // `setupToolbar` মেথড কল করে টাইটেল এবং ব্যাক বাটন সেট করা
        setupToolbar("সংক্ষিপ্ত জীবনী", true);

        textView2 = findViewById(R.id.txt2Id);
        textView2.setText(Html.fromHtml(getString(R.string.omar_ibn_khattab_biography)));



    }
}