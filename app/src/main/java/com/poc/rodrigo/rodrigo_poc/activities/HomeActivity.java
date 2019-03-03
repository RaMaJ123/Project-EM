package com.poc.rodrigo.rodrigo_poc.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.poc.rodrigo.rodrigo_poc.R;
import com.poc.rodrigo.rodrigo_poc.adapters.ViewPagerAdapter;
import com.poc.rodrigo.rodrigo_poc.fragments.MapsFragment;
import com.poc.rodrigo.rodrigo_poc.fragments.SensorDataFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.pager)
    protected ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    @BindView(R.id.tabs)
    protected TabLayout tabLayout;
    @BindView(R.id.logout)
    protected FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new MapsFragment(), getString(R.string.mapTitle));
        viewPagerAdapter.addFragment(new SensorDataFragment(), getString(R.string.sensorDataTitle));
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(HomeActivity.this, "You are being logged out", Toast.LENGTH_SHORT);
        Intent mainActivityIntent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(mainActivityIntent);
    }

}
