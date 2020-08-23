package com.sofdigitalhackathon.libertypolls.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.TypedArrayUtils;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.data.GlobalData;
import com.sofdigitalhackathon.libertypolls.hepler.SignatureHelper;
import com.sofdigitalhackathon.libertypolls.model.Building;
import com.sofdigitalhackathon.libertypolls.model.Flat;
import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.User;
import com.sofdigitalhackathon.libertypolls.network.PollApi;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginActivity extends AppCompatActivity {
    Spinner houseDropDown;
    Spinner flatDropDown;
    ConstraintLayout linearLayout;
    Building selectedHouse = null;
    Flat selectedFlat = null;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitReferences();
        Init();
    }


    private void InitReferences() {
        houseDropDown = findViewById(R.id.login_house_picker);
        flatDropDown = findViewById(R.id.login_flat_picker);
        bLogin = findViewById(R.id.login_button);
        linearLayout = findViewById(R.id.login_layout);
    }

    private void Init() {
        linearLayout.setVisibility(View.GONE);
        flatDropDown.setEnabled(false);
        bLogin.setEnabled(false);
        if (isKeysGenerated()) {
            SharedPreferences sharedPreferences = getSharedPreferences("namefile", Context.MODE_PRIVATE);
            String fromStorePublic = sharedPreferences.getString("public", new String());
            Observable<List<User>> userResponse = new PollApi(getBaseContext()).GetUsers();
            userResponse.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<User>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<User> users) {
                            User registeredUser = null;
                            for (User user : users) {
                                if (user.getPublic_key().equals(fromStorePublic.trim())) {
                                    registeredUser = user;
                                    if(user.isActivated())
                                        GoNextActivity(user);
                                    else
                                        GoActivationActivity(user);
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(getApplicationContext(), "Что то пошло не так", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } else {
            linearLayout.setVisibility(View.VISIBLE);
            GetHouses();
        }
    }

    private boolean isKeysGenerated() {
        SharedPreferences sharedPreferences = getSharedPreferences("namefile", Context.MODE_PRIVATE);
        String fromStorePublic = sharedPreferences.getString("public", new String());
        String fromStoreSecret = sharedPreferences.getString("secret", new String());
        KeyPair key;
        //Generate new Keys and store
        if (fromStorePublic.length() == 0 || fromStoreSecret.length() == 0) {
            key = SignatureHelper.Generate();
            String publicKey = Base64.encodeToString(key.getPublic().getEncoded(), Base64.URL_SAFE);
            String secretKey = Base64.encodeToString(key.getPrivate().getEncoded(), Base64.URL_SAFE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear(); //[important] Clearing your editor before using it.
            editor.putString("public", publicKey.trim());
            editor.putString("secret", secretKey.trim());
            editor.commit();
            return false;
        } else
            return true;
    }

    private void GetHouses() {
        Observable<List<Building>> response = new PollApi(this).GetBuildings();
        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Building>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Building> buildings) {
                        ArrayAdapter<Building> adapter = new ArrayAdapter<Building>(getBaseContext(), android.R.layout.simple_spinner_item, buildings);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        houseDropDown.setAdapter(adapter);
                        houseDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                                selectedHouse = (Building) adapterView.getItemAtPosition(pos);
                                bLogin.setEnabled(false);
                                flatDropDown.setEnabled(false);
                                flatDropDown.setAdapter(null);
                                GetFlat(selectedHouse);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void GetFlat(Building selectedHouse) {
        Observable<List<Flat>> response = new PollApi(this).GetFlats();

        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Flat>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Flat> flats) {
                        flatDropDown.setEnabled(true);
                        List<Flat> filteredFlats = new ArrayList<>();
                        for (Flat flat : flats) {
                            if (flat.getBuilding().getId() == selectedHouse.getId()) {
                                filteredFlats.add(flat);
                            }
                        }
                        ArrayAdapter<Flat> adapter = new ArrayAdapter<Flat>(getBaseContext(), android.R.layout.simple_spinner_item, filteredFlats);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        flatDropDown.setAdapter(adapter);
                        flatDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                                selectedFlat = (Flat) adapterView.getItemAtPosition(pos);
                                bLogin.setEnabled(true);
                                bLogin.setOnClickListener(view1 -> {
                                    Login(selectedHouse, selectedFlat);
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void Login(Building selectedHouse, Flat selectedFlat) {
        SharedPreferences sharedPreferences = getSharedPreferences("namefile", Context.MODE_PRIVATE);
        String fromStorePublic = sharedPreferences.getString("public", new String());
        String fromStoreSecret = sharedPreferences.getString("secret", new String());
        Observable<ResponseBody> response = new PollApi(this).CreateUser(selectedFlat.getId(), fromStorePublic);
        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        String s = responseBody.toString();
                        Observable<List<User>> userResponse = new PollApi(getBaseContext()).GetUsers();
                        userResponse.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<List<User>>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(List<User> users) {
                                        User registeredUser = null;
                                        for (User user : users) {
                                            if (user.getPublic_key().equals(fromStorePublic.trim())) {
                                                registeredUser = user;
                                                if(user.isActivated())
                                                    GoNextActivity(user);
                                                else
                                                    GoActivationActivity(user);
                                                break;
                                            }
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Toast.makeText(getApplicationContext(), "Что то пошло не так", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {
                        String s = e.getMessage();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void GoNextActivity(User user) {
        GlobalData.currentUser = user;
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", new Gson().toJson(user));
        startActivity(intent);
    }

    private void GoActivationActivity(User user) {
        GlobalData.currentUser = user;
        Intent intent = new Intent(this, UserActivationActivity.class);
        intent.putExtra("user", new Gson().toJson(user));
        startActivity(intent);
    }
}
