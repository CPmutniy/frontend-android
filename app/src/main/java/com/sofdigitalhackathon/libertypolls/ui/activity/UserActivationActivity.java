package com.sofdigitalhackathon.libertypolls.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.model.User;

public class UserActivationActivity extends AppCompatActivity {
    TextView tvId;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activation);

        user = new Gson().fromJson(getIntent().getStringExtra("user"),User.class);
        tvId = findViewById(R.id.user_activation_id);
        tvId.setText(String.valueOf(user.getId()));
    }
}
