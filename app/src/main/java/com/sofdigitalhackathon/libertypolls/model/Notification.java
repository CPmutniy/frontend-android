package com.sofdigitalhackathon.libertypolls.model;

public class Notification {
    public final static int TYPE_IMPORTANT = 0;
    public final static int TYPE_REPAIR = 1;
    public final static int TYPE_INDICATIONS = 2;
    String title;
    String description;
    int type;

    public Notification(String title, String description, int type) {
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getType() {
        return type;
    }
}
