package com.sofdigitalhackathon.libertypolls.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.data.GlobalData;

public class SettingsFragment extends Fragment {
    TextInputEditText tiName;
    TextInputEditText tiSurname;
    TextInputEditText tiPatronimyc;
    TextInputEditText tiBuilding;
    TextInputEditText tiFlat;
    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(),"Settings",Toast.LENGTH_LONG).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitReferences();
        Init();
    }

    private void InitReferences() {
        tiName = getView().findViewById(R.id.settings_name);
        tiSurname = getView().findViewById(R.id.settings_surname);
        tiPatronimyc = getView().findViewById(R.id.settings_patronymic);
        tiBuilding = getView().findViewById(R.id.settings_building);
        tiFlat = getView().findViewById(R.id.settings_flat);
    }
    private void Init() {
        tiName.setText(GlobalData.currentUser.getName());
        tiSurname.setText(GlobalData.currentUser.getSurname());
        tiPatronimyc.setText(GlobalData.currentUser.getPatronymic());
        tiBuilding.setText(GlobalData.currentUser.getFlat().getBuilding().getName());
        tiFlat.setText("Квартира " + GlobalData.currentUser.getFlat().getFlatNum());
    }

}
