package com.nruotsalainen.notes_frontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class Note {

    private long id;
    private String value;
    private ZonedDateTime dateCreated;

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("date_created")
    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", Value: \"" + getValue() + "\", Date Created: " + getDateCreated();
    }
}
