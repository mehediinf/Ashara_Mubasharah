package com.ashara_mubasharah.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends BaseActivity {

    private ListView listView;
    BaseAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // `setupToolbar` মেথড কল করে টাইটেল এবং ব্যাক বাটন সেট করা
        setupToolbar("আশারায়ে মুবাশশারাহ", false);

        listView = findViewById(R.id.listViewId);


        // String array from resources
        final String[] sahabiNameArray = getResources().getStringArray(R.array.ashara_mubasharah_name);


        // Directly set drawable resource IDs in an array
        final int[] imgView = {
                R.drawable.one, R.drawable.two, R.drawable.three,
                R.drawable.four, R.drawable.five, R.drawable.six,
                R.drawable.seven, R.drawable.eight, R.drawable.nine,
                R.drawable.ten
        };

        // Custom adapter setup
        // Set adapter to listView

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return sahabiNameArray.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }





            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
//                    LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    convertView = layoutInflater.inflate(R.layout.sample_view, parent, false);
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.sample_view,parent,false);
                }

                ImageView imageView = convertView.findViewById(R.id.imgSampleViewId);
                TextView textView = convertView.findViewById(R.id.txtSampleId);

                // Setting image and text
                imageView.setImageResource(imgView[position]);
                textView.setText(sahabiNameArray[position]);

                return convertView;
            }
        });



        // ListView item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String value = sahabiNameArray[position];

                if (value.equals("আবু বকর (রা.)")){

                    Intent intent = new Intent(MainActivity.this,AbuBakrActivity.class);
                    startActivity(intent);
                }if (value.equals("ওমর ইবনে খাত্তাব (রা.)")){

                    Intent intent = new Intent(MainActivity.this,Umar_ibn_al_khattabActivity.class);
                    startActivity(intent);
                }if (value.equals("উসমান ইবনে আফফান (রা.)")){

                    Intent intent = new Intent(MainActivity.this,Uthman_ibn_affanActivity.class);
                    startActivity(intent);
                }if (value.equals("আলী ইবনে আবু তালিব (রা.)")){

                    Intent intent = new Intent(MainActivity.this,Ali_ibn_abi_talibActivity.class);
                    startActivity(intent);
                }if (value.equals("তালহা ইবনে উবাইদুল্লাহ (রা.)")){

                    Intent intent = new Intent(MainActivity.this,Talhah_ibn_ubaidullahActivity.class);
                    startActivity(intent);
                }if (value.equals("জুবায়ের ইবনে আওয়াম (রা.)")){

                    Intent intent = new Intent(MainActivity.this,Zubayr_ibn_al_awamActivity.class);
                    startActivity(intent);
                }if (value.equals("আব্দুর রহমান ইবনে আওফ (রা.)")){

                    Intent intent = new Intent(MainActivity.this,Abdur_rahman_ibn_awfActivity.class);
                    startActivity(intent);
                }if (value.equals("সাআদ ইবনে আবু ওয়াক্কাস (রা.)")){

                    Intent intent = new Intent(MainActivity.this,Saad_ibn_abu_waqqasActivity.class);
                    startActivity(intent);
                }if (value.equals("সাঈদ ইবনে যায়েদ (রা.)")){

                    Intent intent = new Intent(MainActivity.this,Saeed_ibn_zaydActivity.class);
                    startActivity(intent);
                }if (value.equals("আবু উবাইদাহ ইবনে জাররাহ (রা.)")){

                    Intent intent = new Intent(MainActivity.this,Abu_ubaidah_ibn_al_jarrahActivity.class);
                    startActivity(intent);
                }




            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);

        MenuItem menuItem = menu.findItem(R.id.menuSrcId);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                //adapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.menuSettingId){
            Toast.makeText(MainActivity.this,"Settings",Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId()==R.id.menuShareId){

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            String subject ="আশারায়ে মুবাশশারাহ অ্যাপ";
            String body = "আশারায়ে মুবাশশারাহ অ্যাপ থেকে ইসলামের দশজন বিশিষ্ট সাহাবির সম্পর্কে জানতে পারবেন, যাদের মুহাম্মদ (সা.) জীবদ্দশায় জান্নাতের সুসংবাদ দিয়েছিলেন। \n App Link: com.ashara_mubasharah.myapplication";

            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);

            startActivity(Intent.createChooser(intent,"Share with"));

        }else if (item.getItemId()==R.id.menuFeedbackId){

            Intent intent = new Intent(getApplicationContext(),FeedbackActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
