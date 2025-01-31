package com.ashara_mubasharah.myapplication;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import java.util.Locale;

public class SettingsActivity extends BaseActivity {

    private SwitchCompat themeSwitch, notificationSwitch;
    private Spinner languageSpinner;
    private SharedPreferences sharedPreferences;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // টুলবার সেটআপ
        setupToolbar("Setting",true);

        // SharedPreferences ইনিশিয়ালাইজ করা
        sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);

        // থিম সুইচ ইনিশিয়ালাইজ
        themeSwitch = findViewById(R.id.themeSwitch);
        themeSwitch.setChecked(isDarkModeEnabled());

        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                sharedPreferences.edit().putBoolean("DarkMode", true).apply();  // Dark mode সেভ
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                sharedPreferences.edit().putBoolean("DarkMode", false).apply(); // Light mode সেভ
            }
        });

        // নোটিফিকেশন সুইচ ইনিশিয়ালাইজ
        notificationSwitch = findViewById(R.id.notificationSwitch);
        boolean isNotificationEnabled = sharedPreferences.getBoolean("NotificationEnabled", true);
        notificationSwitch.setChecked(isNotificationEnabled);

        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("NotificationEnabled", isChecked);
            editor.apply();
            if (isChecked) {
                enableNotifications();
            } else {
                disableNotifications();
            }
        });

        // ভাষা পরিবর্তন স্পিনার ইনিশিয়ালাইজ
        languageSpinner = findViewById(R.id.languageSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                switch (position) {
                    case 0: // English
                        setLocale("en");
                        break;
                    case 1: // Bangla
                        setLocale("bn");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    private boolean isDarkModeEnabled() {
        int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return nightMode == Configuration.UI_MODE_NIGHT_YES;
    }

    private void enableNotifications() {
        Toast.makeText(this, "Notifications Enabled", Toast.LENGTH_SHORT).show();
    }

    private void disableNotifications() {
        Toast.makeText(this, "Notifications Disabled", Toast.LENGTH_SHORT).show();
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        // For Android 7.0 and above, we should use this
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        // To refresh the activity after locale change
        recreate();  // অ্যাপ রিলঞ্চ করার জন্য
    }
}
