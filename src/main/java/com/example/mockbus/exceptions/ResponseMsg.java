package com.example.mockbus.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMsg {

    private LocalTime timestamp;
    private String field;
    private String message;

    public ResponseMsg() {
        this.timestamp = LocalTime.now();
    }

    public ResponseMsg(String field, String message) {
        this();
        this.field = field;
        this.message = message;
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

}