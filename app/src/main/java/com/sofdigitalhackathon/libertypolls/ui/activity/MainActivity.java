package com.sofdigitalhackathon.libertypolls.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.model.User;
import com.sofdigitalhackathon.libertypolls.ui.fragment.NotificationFragment;
import com.sofdigitalhackathon.libertypolls.ui.fragment.PollMainFragment;
import com.sofdigitalhackathon.libertypolls.ui.fragment.SettingsFragment;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnBottomNavbar;
    FrameLayout fFragmentContainer;
    User user = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new Gson().fromJson(getIntent().getStringExtra("user"),User.class);
        InitReference();
        Init();
        Bundle args = new Bundle();
        args.putString("user",new Gson().toJson(user));
        loadFragment(new PollMainFragment(),args);
    }


    private void InitReference() {
        bnBottomNavbar  = findViewById(R.id.activity_main_bottom_navbar);
        fFragmentContainer = findViewById(R.id.activity_main_fragment_container);
    }

    private void Init() {

        bnBottomNavbar.setOnNavigationItemSelectedListener(menuItem -> {
            Fragment fragment;
            switch (menuItem.getItemId()){
                case R.id.bottom_item_polls:
                    fragment = new PollMainFragment();
                    Bundle args = new Bundle();
                    args.putString("user",new Gson().toJson(user));
                    loadFragment(fragment,args);
                    return true;
                case R.id.bottom_item_notification:
                    fragment = new NotificationFragment();
                    loadFragment(fragment,null);
                    return true;
                case R.id.bottom_item_settings:
                    fragment = new SettingsFragment();
                    loadFragment(fragment,null);
                    return true;
            }
            return false;
        });
    }
    private void loadFragment(Fragment fragment,Bundle args) {
        // load fragment
        fragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

    }
}
