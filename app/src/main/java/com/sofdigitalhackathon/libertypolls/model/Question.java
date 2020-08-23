package com.sofdigitalhackathon.libertypolls.model;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.sofdigitalhackathon.libertypolls.network.PollApi;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Question {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String title;
    @SerializedName("description")
    String description;
    @SerializedName("voting")
    int pollId;
    @SerializedName("answersinfo")
    List<Answer> answerList;



    public int getId() {
        return id;
    }


    public int getPollId() {
        return pollId;
    }

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
    public List<Answer> getAnswerList() {
        return answerList;
    }
    public Observable<Question> UpdateFromServer(Context context){
        return new PollApi(context).GetQuestion(this.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
