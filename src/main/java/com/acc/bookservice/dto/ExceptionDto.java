package com.acc.bookservice.dto;

import java.util.Date;

/**
 * ExceptionDTO Class to set error details
 */
public class ExceptionDto {

    private Date timestamp;
    private String error;
    private String description;

    public ExceptionDto(Date timestamp, String error, String description) {
        super();
        this.timestamp = timestamp;
        this.error = error;
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExceptionDto{" +
                "timestamp=" + timestamp +
                ", error='" + error + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
