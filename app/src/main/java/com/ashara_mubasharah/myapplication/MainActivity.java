// MainActivity.java
package com.ashara_mubasharah.myapplication;

import static android.os.Build.VERSION_CODES.P;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ListView listView;
    private CustomAdapter adapter;
    private List<String> sahabiNameList;
    private int[] imgView = {
            R.drawable.one, R.drawable.two, R.drawable.three,
            R.drawable.four, R.drawable.five, R.drawable.six,
            R.drawable.seven, R.drawable.eight, R.drawable.nine,
            R.drawable.ten
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar("আশারায়ে মুবাশশারাহ", false);
        listView = findViewById(R.id.listViewId);

        sahabiNameList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.ashara_mubasharah_name)));

        adapter = new CustomAdapter(sahabiNameList,imgView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener((parent, view, position, id) ->{
            String value = (String) adapter.getItem(position);
            Class<?> activityClass = getActivityClass(value);
            if (activityClass != null){
                Intent intent = new Intent(this,activityClass);
                startActivity(intent);
            }

        });
    }

    private Class<?> getActivityClass(String name) {
        switch (name) {
            case "আবু বকর (রা.)": return AbuBakrActivity.class;
            case "ওমর ইবনে খাত্তাব (রা.)": return Umar_ibn_al_khattabActivity.class;
            case "উসমান ইবনে আফফান (রা.)": return Uthman_ibn_affanActivity.class;
            case "আলী ইবনে আবু তালিব (রা.)": return Ali_ibn_abi_talibActivity.class;
            case "তালহা ইবনে উবাইদুল্লাহ (রা.)": return Talhah_ibn_ubaidullahActivity.class;
            case "জুবায়ের ইবনে আওয়াম (রা.)": return Zubayr_ibn_al_awamActivity.class;
            case "আব্দুর রহমান ইবনে আওফ (রা.)": return Abdur_rahman_ibn_awfActivity.class;
            case "সাআদ ইবনে আবু ওয়াক্কাস (রা.)": return Saad_ibn_abu_waqqasActivity.class;
            case "সাঈদ ইবনে যায়েদ (রা.)": return Saeed_ibn_zaydActivity.class;
            case "আবু উবাইদাহ ইবনে জাররাহ (রা.)": return Abu_ubaidah_ibn_al_jarrahActivity.class;
            default: return null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);

        MenuItem menuItem = menu.findItem(R.id.menuSrcId);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("সাহাবির নাম লিখুন...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSettingId) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
        } else if (item.getItemId() == R.id.menuShareId) {
            shareApp();
        } else if (item.getItemId() == R.id.menuFeedbackId) {
            startActivity(new Intent(getApplicationContext(), FeedbackActivity.class));
        } else if (item.getItemId() == R.id.menuAboutId) {
            startActivity(new Intent(getApplicationContext(), GitHubActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    private void shareApp() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String subject = "আশারায়ে মুবাশশারাহ অ্যাপ";
        String body = "এই অ্যাপ থেকে দশজন সাহাবির সম্পর্কে জানতে পারবেন! \nApp Link: com.ashara_mubasharah.myapplication";
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(intent, "Share with"));
    }



    }
