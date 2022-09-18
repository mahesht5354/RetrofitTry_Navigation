package com.example.retrofittry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.retrofittry.GitHubRetrofitService;
import com.example.retrofittry.Model.Datum;
import com.example.retrofittry.Model.Source;
import com.example.retrofittry.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TabLayout tl;
    ViewPager vp;
    RecyclerView recyclerView;
    Button b;
    Retrofit retrofit;
    GitHubRetrofitService gfs;
    Gson gson;
    public DrawerLayout drawerLayout;
    private Toolbar toolbar;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView nvDrawer;
    private DrawerLayout mDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recyclerView = findViewById(R.id.recyclerView);
        /*tl = findViewById(R.id.tabLayout);
        vp = findViewById(R.id.viewpager);*/
        /*Vpadapter vpadapter = new Vpadapter(getSupportFragmentManager());
        vp.setAdapter(vpadapter);
        tl.setupWithViewPager(vp);*/
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        // This will display an Up icon (<-), we will replace it with hamburger later
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //b = findViewById(R.id.button);
        //b.setVisibility(View.GONE);
        mDrawer = (DrawerLayout) findViewById(R.id.my_drawer_layout);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_entertainment:
                fragmentClass = GreenFragment.class;
                break;
            case R.id.nav_politics:
                fragmentClass = YellowFragment.class;
                break;
            default:
                fragmentClass = RedFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }






    /*class Vpadapter extends FragmentStatePagerAdapter
    {

        public Vpadapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return new RedFragment();
                case 1: return new GreenFragment();
                case 2: return new YellowFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return "Sports";
                case 1: return "Entertainment";
                case 2: return "Politics";
            }
            return "null";
        }


    }*/
}