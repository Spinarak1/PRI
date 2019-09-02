package com.example.rateit;

import android.content.Intent;
import android.os.Bundle;

import com.example.rateit.ui.gallery.GalleryFragment;
import com.example.rateit.ui.gallery.GalleryViewModel;
import com.example.rateit.ui.home.HomeFragment;
import com.example.rateit.ui.home.HomeViewModel;
import com.example.rateit.ui.send.SendFragment;
import com.example.rateit.ui.send.SendViewModel;
import com.example.rateit.ui.slideshow.SlideshowFragment;
import com.example.rateit.ui.slideshow.SlideshowViewModel;
import com.example.rateit.ui.tools.ToolsFragment;
import com.example.rateit.ui.tools.ToolsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.new_release, R.id.popular,
                R.id.favourites, R.id.my_account, R.id.settings)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this, HomeViewModel.class));
        } else if (id == R.id.new_release) {
            startActivity(new Intent(this, GalleryViewModel.class));
        } else if (id == R.id.popular) {
            startActivity(new Intent(this, SendViewModel.class));
        } else if (id == R.id.favourites) {
            startActivity(new Intent(this, SlideshowViewModel.class));
        } else if (id == R.id.my_account) {
            startActivity(new Intent(this, ToolsViewModel.class));
        } else if (id == R.id.settings) {
            startActivity(new Intent(this, SendViewModel.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
