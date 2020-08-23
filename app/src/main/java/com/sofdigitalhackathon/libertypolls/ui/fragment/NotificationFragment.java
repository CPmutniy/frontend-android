package com.sofdigitalhackathon.libertypolls.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.adapters.NotificationItemAdapter;
import com.sofdigitalhackathon.libertypolls.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {


    RecyclerView rvNotifications;
    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(),"Notification",Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvNotifications = getView().findViewById(R.id.notification_recycleview);
        List<Notification> notifications = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.notification_titles);
        String[] descriptions = getResources().getStringArray(R.array.notification_descriptions);
        int[] types = getResources().getIntArray(R.array.notification_modes);
        for(int i = 0; i< titles.length; i++){
            notifications.add(new Notification(titles[i],descriptions[i],types[i]));
        }
        NotificationItemAdapter adapter = new NotificationItemAdapter(getContext(),notifications);
        rvNotifications.setAdapter(adapter);
    }
}
