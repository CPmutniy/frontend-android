package com.sofdigitalhackathon.libertypolls.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Poll {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String title;
    @SerializedName("adress")
    int buildingId;
    @SerializedName("initiatorinfo")
    User initiator;
    String time;
    @SerializedName("questionsinfo")
    List<Question> questionList;

    public String getTitle() {
        return title;
    }
    public User getInitiator() {
        return initiator;
    }

    public int getId() {
        return id;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public String getTime() {
        return time;
    }
    public List<Question> getQuestionList() {
        return questionList;
    }

    public interface OnPollInteract{
        public void onClick(Poll poll);
    }
}
