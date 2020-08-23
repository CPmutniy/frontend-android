package com.sofdigitalhackathon.libertypolls.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.data.GlobalData;
import com.sofdigitalhackathon.libertypolls.model.User;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserActivationActivity extends AppCompatActivity {
    TextView tvId;
    Button bCheck;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activation);

        user = new Gson().fromJson(getIntent().getStringExtra("user"),User.class);
        tvId = findViewById(R.id.user_activation_id);
        bCheck = findViewById(R.id.activation_check);

        tvId.setText(String.valueOf(user.getId()));
        bCheck.setOnClickListener(view -> {
            user.UpdateFromServer(getBaseContext())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<User>(){

                        @Override
                        public void onSubscribe(Disposable d) {
                            bCheck.setEnabled(false);
                            Toast.makeText(getBaseContext(),"Проверяем",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNext(User user) {
                            bCheck.setEnabled(true);
                            GlobalData.currentUser = user;
                            Toast.makeText(getBaseContext(),"Добро пожаловать",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("user", new Gson().toJson(user));
                            startActivity(intent);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            bCheck.setEnabled(true);

                        }
                    });
        });
    }
}
