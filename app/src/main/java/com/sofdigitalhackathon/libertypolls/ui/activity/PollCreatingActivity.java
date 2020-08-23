package com.sofdigitalhackathon.libertypolls.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.adapters.PollCreatorAdapter;
import com.sofdigitalhackathon.libertypolls.data.GlobalData;
import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.Question;
import com.sofdigitalhackathon.libertypolls.network.PollApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PollCreatingActivity extends AppCompatActivity {

    Button bAdd;
    Button bCreate;
    RecyclerView rvQuestions;
    TextInputEditText tiTtitle;
    List<QuestionItem> allQuestions = new ArrayList<>();
    PollCreatorAdapter adapter;
    int successRequests = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_creating);
        InitReference();
        Init();
    }


    private void InitReference() {
        bAdd = findViewById(R.id.poll_creator_add);
        rvQuestions = findViewById(R.id.poll_creator_recycleview);
        bCreate = findViewById(R.id.poll_creator_create);
        tiTtitle = findViewById(R.id.poll_creator_title);
    }

    private void Init() {
        adapter = new PollCreatorAdapter(this, allQuestions);
        rvQuestions.setAdapter(adapter);
        bAdd.setOnClickListener(view -> {
            allQuestions.add(new QuestionItem());
            adapter.notifyDataSetChanged();
        });
        bCreate.setOnClickListener(view -> {
            CreatePoll();
        });
    }

    private void CreatePoll() {
        String title = tiTtitle.getText().toString();
        if (title.length() == 0) {
            Toast.makeText(this, "Заполните ззаголовок", Toast.LENGTH_LONG).show();
            return;
        }
        Observable<Poll> response = new PollApi(this).CreatePoll(title, GlobalData.currentUser.getFlat().getBuildingId(), GlobalData.currentUser.getId());
        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Poll>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Poll poll) {
                        CreateQuestions(poll);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @SuppressLint("CheckResult")
    private void CreateQuestions(Poll poll) {
        boolean isAllOk = true;
        for (QuestionItem item : allQuestions) {
            if (item.getTitle().length() != 0 && item.getDescription().length() != 0)
                isAllOk = true;
            else
                isAllOk = false;

        }
        if(!isAllOk){
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show();
            return;
        }
        PollApi api = new PollApi(this);
        ArrayList<Observable<Question>> requests = new ArrayList<Observable<Question>>();
        for (QuestionItem item : allQuestions) {
            Observable<Question> response  = api.CreateQuestion(item.getTitle(), item.getDescription(),poll.getId());
            response.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Question>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Question question) {
                            CheckRequests();
                        }

                        @Override
                        public void onError(Throwable e) {
                            HandleError();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }

    private void HandleError() {
        Toast.makeText(this,"ОШИБКААААААа",Toast.LENGTH_LONG).show();

    }

    private void CheckRequests() {
        successRequests++;
        if(successRequests == allQuestions.size())
        {
            Toast.makeText(this,"Собрание создано",Toast.LENGTH_LONG).show();
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    public class QuestionItem {
        String title = "";
        String description = "";

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
