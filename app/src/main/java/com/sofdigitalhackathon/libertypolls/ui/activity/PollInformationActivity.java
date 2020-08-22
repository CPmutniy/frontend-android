package com.sofdigitalhackathon.libertypolls.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.adapters.PollInformationAdapter;
import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.User;

public class PollInformationActivity extends AppCompatActivity {

    ViewPager viewPager;
    Poll poll = null;
    TextView tvTitle;
    TextView tvInitiatorName;
    TextView tvDescription;
//    android:id="@+id/poll_information_initiator_name"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_information);
        InitReferences();
        Init();
    }


    private void InitReferences() {
        viewPager = findViewById(R.id.poll_information_viewpager);
        tvTitle = findViewById(R.id.poll_information_title);
        tvInitiatorName = findViewById(R.id.poll_information_initiator_name);
        tvDescription = findViewById(R.id.poll_information_description);
    }


    private void Init() {
        poll = new Gson().fromJson(getIntent().getStringExtra("poll"),Poll.class);
        User initiator = poll.getInitiator();
        String fullName = initiator.getName() + " " +initiator.getSurname() ;
        tvTitle.setText(poll.getTitle());
        tvInitiatorName.setText(fullName);
        tvDescription.setText(poll.getDescription());
        PollInformationAdapter adapter = new PollInformationAdapter(getSupportFragmentManager(),poll.getQuestionList());
        viewPager.setAdapter(adapter);
    }
}
