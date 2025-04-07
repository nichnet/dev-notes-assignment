package com.nruotsalainen.notes_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nruotsalainen.notes_backend.utils.DateUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public final class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "value")
    private String value;

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", Value: \"" + getValue() + "\"";
    }
}
