package com.sofdigitalhackathon.libertypolls.model;

import java.util.List;

public class Poll {
    String title;
    String description;
    User initiator;
    String time;
    List<Question> questionList;

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
    public User getInitiator() {
        return initiator;
    }

    public void setInitiator(User initiator) {
        this.initiator = initiator;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
    }
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public interface OnPollInteract{
        public void onClick(Poll poll);
    }
}
