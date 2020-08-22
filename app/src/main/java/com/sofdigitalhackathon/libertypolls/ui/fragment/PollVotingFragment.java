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
import android.widget.TextView;

import com.google.gson.Gson;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.Question;

public class PollVotingFragment extends Fragment {

    TextView tvQuestion;
    TextView tvQuestionTitle;
    TextView tvQuestionSolution;
    Question question;
    int number;
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
        question = new Gson().fromJson(getArguments().getString("question"),Question.class);
        number = getArguments().getInt("number");

        return inflater.inflate(R.layout.fragment_poll_voting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvQuestion = getView().findViewById(R.id.poll_voting_number);
        tvQuestionTitle = getView().findViewById(R.id.poll_voting_title);
        tvQuestionSolution = getView().findViewById(R.id.poll_voting_solution);
        UpdateViews();
    }

    private void UpdateViews() {
        tvQuestion.setText("Вопрос " + number);
        if(question != null){
            tvQuestionTitle.setText(question.getTitle());
            tvQuestionSolution.setText(question.getSolution());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
