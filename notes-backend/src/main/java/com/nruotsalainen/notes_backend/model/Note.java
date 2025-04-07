package com.nruotsalainen.notes_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nruotsalainen.notes_backend.utils.DateUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public final class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "value")
    private String value;
    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @PrePersist
    protected void onCreate() {
        if (this.dateCreated == null) {
            this.dateCreated = DateUtils.convertToTimezone(ZonedDateTime.now(), "UTC");
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateCreated = DateUtils.convertToTimezone(this.dateCreated, "UTC");
    }

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

    public void setTimezone(String timezoneId) {
        this.dateCreated = DateUtils.convertToTimezone(this.dateCreated, timezoneId);
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", Value: \"" + getValue() + "\", Date Created: " + getDateCreated();
    }
}
