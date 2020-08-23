package com.sofdigitalhackathon.libertypolls.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.adapters.PollInformationAdapter;
import com.sofdigitalhackathon.libertypolls.model.Flat;
import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.User;
import com.sofdigitalhackathon.libertypolls.network.PollApi;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PollInformationActivity extends AppCompatActivity {

    ViewPager viewPager;
    Poll poll = null;
    TextView tvTitle;
    TextView tvInitiatorName;
    TextView tvDescription;
    TextView tvLocation;
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
        tvLocation = findViewById(R.id.poll_information_initiator_location);
    }


    private void Init() {
        poll = new Gson().fromJson(getIntent().getStringExtra("poll"),Poll.class);
        User initiator = poll.getInitiator();
        String fullName = initiator.getName() + " " +initiator.getSurname() ;
        tvTitle.setText(poll.getTitle());
        tvInitiatorName.setText(fullName);
        int flatNum = poll.getInitiator().getFlatId();

        Observable<Flat> response = new PollApi(this).GetFlat(flatNum);
        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Flat>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Flat flat) {
                        String buildingAddress = flat.getBuilding().getName();
                        tvLocation.setText(buildingAddress +"\n" + String.format("Кв. №%d",flatNum));

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //tvDescription.setText(poll.getDescription());
        PollInformationAdapter adapter = new PollInformationAdapter(getSupportFragmentManager(),poll.getQuestionList());
        viewPager.setAdapter(adapter);
    }
}
