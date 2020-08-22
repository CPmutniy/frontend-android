package com.sofdigitalhackathon.libertypolls.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.ui.fragment.NotificationFragment;
import com.sofdigitalhackathon.libertypolls.ui.fragment.PollFragment;
import com.sofdigitalhackathon.libertypolls.ui.fragment.SettingsFragment;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnBottomNavbar;
    FrameLayout fFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitReference();
        Init();
        loadFragment(new PollFragment());
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
                    fragment = new PollFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.bottom_item_notification:
                    fragment = new NotificationFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.bottom_item_settings:
                    fragment = new SettingsFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        });
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
