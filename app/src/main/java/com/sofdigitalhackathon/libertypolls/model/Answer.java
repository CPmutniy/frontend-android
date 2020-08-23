package com.sofdigitalhackathon.libertypolls.model;

import com.google.gson.annotations.SerializedName;

public class Answer {
    public static final String ANSWER_YES = "yes";
    public static final String ANSWER_NO = "no";
    public static final String ANSWER_DESIST = "desist";

    @SerializedName("id")
    int flatId;
    @SerializedName("answer")
    String answer;
    @SerializedName("person")
    int personId;
    @SerializedName("question")
    int questionId;
    @SerializedName("time")
    String time;

    public int getId() {
        return flatId;
    }

    public int getPerson() {
        return personId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }

    // TODO reimplement, convert string to Calendar
    public String getTime() {
        return time;
    }
}
