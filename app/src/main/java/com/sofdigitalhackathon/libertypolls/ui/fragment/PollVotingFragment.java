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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.data.GlobalData;
import com.sofdigitalhackathon.libertypolls.model.Answer;
import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.Question;
import com.sofdigitalhackathon.libertypolls.network.PollApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class PollVotingFragment extends Fragment {

    private TextView tvQuestion;
    private TextView tvQuestionTitle;
    private TextView tvQuestionSolution;
    private Button bAgree;
    private Button bDisagree;
    private Button bDesist;
    private Question currentQuestion;
    private int number;
    public PollVotingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentQuestion = new Gson().fromJson(getArguments().getString("question"),Question.class);
        number = getArguments().getInt("number");

        return inflater.inflate(R.layout.fragment_poll_voting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitReferences();
        Init();
    }


    private void InitReferences() {
        tvQuestion = getView().findViewById(R.id.poll_voting_number);
        tvQuestionTitle = getView().findViewById(R.id.poll_voting_title);
        tvQuestionSolution = getView().findViewById(R.id.poll_voting_solution);
        tvQuestionSolution = getView().findViewById(R.id.poll_voting_solution);
        tvQuestionSolution = getView().findViewById(R.id.poll_voting_solution);
        bAgree = getView().findViewById(R.id.poll_voting_agree);
        bDisagree = getView().findViewById(R.id.poll_voting_disagree);
        bDesist = getView().findViewById(R.id.poll_voting_desist);
    }

    private void Init() {
        bAgree.setOnClickListener(view -> {Vote(Answer.ANSWER_YES);});
        bDisagree.setOnClickListener(view -> {Vote(Answer.ANSWER_NO);});
        bDesist.setOnClickListener(view -> {Vote(Answer.ANSWER_DESIST);});
        InitButtons();
        UpdateViews();
    }

    private void InitButtons() {
        currentQuestion.UpdateFromServer(getContext())
                .subscribe(new Observer<Question>(){

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Question question) {
                        currentQuestion = question;
                        for(Answer answer: currentQuestion.getAnswerList()){
                            if(answer.getPerson() == GlobalData.currentUser.getId()){
                                switch (answer.getAnswer()){
                                    case Answer.ANSWER_YES:
                                        DisableAllButtons();
                                        bAgree.setBackgroundResource(R.color.buttonActive);
                                        break;
                                    case Answer.ANSWER_NO:
                                        DisableAllButtons();
                                        bDisagree.setBackgroundResource(R.color.buttonActive);
                                        break;
                                    case Answer.ANSWER_DESIST:
                                        DisableAllButtons();
                                        bDesist.setBackgroundResource(R.color.buttonActive);
                                        break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    private void DisableAllButtons(){
        bAgree.setEnabled(false);
        bDisagree.setEnabled(false);
        bDesist.setEnabled(false);
    }


    private void Vote(String answer) {
        Calendar currentTime = Calendar.getInstance();
        SimpleDateFormat DjangoDateFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String time = DjangoDateFormater.format(currentTime.getTime());
        Observable<ResponseBody> response = new PollApi(getContext()).CreateAnswer(
                GlobalData.currentUser.getId(),
                currentQuestion.getId(),
                time,
                answer);
        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        DisableAllButtons();
                        Toast.makeText(getContext(),"Засчитываем ваш голос", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InitButtons();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void UpdateViews() {
        tvQuestion.setText("Вопрос " + number);
        if(currentQuestion != null){
            tvQuestionTitle.setText(currentQuestion.getTitle());
            tvQuestionSolution.setText(currentQuestion.getDescription());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
