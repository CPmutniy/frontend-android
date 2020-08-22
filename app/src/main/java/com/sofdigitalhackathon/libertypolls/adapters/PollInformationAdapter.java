package com.sofdigitalhackathon.libertypolls.adapters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.gson.Gson;
import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.Question;
import com.sofdigitalhackathon.libertypolls.ui.fragment.PollVotingFragment;

import java.util.List;

public class PollInformationAdapter extends FragmentPagerAdapter {

    List<Question> allQuestions;

    public PollInformationAdapter(FragmentManager fm, List<Question> questions) {
        super(fm);
        allQuestions = questions;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle arguments = new Bundle();
        arguments.putString("question",new Gson().toJson(allQuestions.get(position)));
        arguments.putInt("number",position);
        PollVotingFragment fragment = new PollVotingFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public int getCount() {
        return allQuestions.size();
    }
}
