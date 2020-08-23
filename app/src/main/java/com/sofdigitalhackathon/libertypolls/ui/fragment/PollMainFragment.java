package com.sofdigitalhackathon.libertypolls.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.adapters.PollItemAdapter;
import com.sofdigitalhackathon.libertypolls.model.Building;
import com.sofdigitalhackathon.libertypolls.model.Flat;
import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.Question;
import com.sofdigitalhackathon.libertypolls.model.User;
import com.sofdigitalhackathon.libertypolls.network.PollApi;
import com.sofdigitalhackathon.libertypolls.ui.activity.PollInformationActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
public class PollMainFragment extends Fragment {

    RecyclerView recyclerView;
    List<Poll> pollList = new ArrayList<>();
    User user;

    public PollMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), "Poll", Toast.LENGTH_LONG).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        user = new Gson().fromJson(getArguments().getString("user"),User.class);
        return inflater.inflate(R.layout.fragment_poll, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitReferences();
        Init();
    }

    private void InitReferences() {
        recyclerView = getView().findViewById(R.id.poll_main_recycleview);
    }

    private void Init() {
        GetPolls();
    }

    private void GetPolls() {
        Observable<Flat> response = new PollApi(getContext()).GetFlat(user.getFlat().getId());
        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Flat>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Flat flat) {
                        List<Poll> filtered = flat.getBuilding().getPolls();
                        PollItemAdapter adapter = new PollItemAdapter(getContext(), filtered);
                        adapter.setOnClickListener(poll -> {
                            Intent intent = new Intent(getContext(), PollInformationActivity.class);
                            intent.putExtra("poll", new Gson().toJson(poll));
                            startActivity(intent);
                        });
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
