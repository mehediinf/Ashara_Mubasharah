package com.ashara_mubasharah.myapplication;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Saad_ibn_abu_waqqasActivity extends AppCompatActivity {

    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saad_ibn_abu_waqqas);

        textView2 = findViewById(R.id.txt2Id);
        textView2.setText(Html.fromHtml(getString(R.string.saad_ibn_abi_waqqas_biography)));


    }
}