package com.jms.presentation.model;


import java.util.Map;

/**
 * @author william zhang
 */
public class Response {
    private int statusCode;                 // refer to ResponseStatus.java
    private String statusDescription;       // refer to ResponseStatus.java
    private Map<String, Object> models;

    public Response() {
    }

    public Response(int statusCode, String statusDescription) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    public Response(int statusCode, String statusDescription, Map models) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.models = models;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Map<String, Object> getModels() {
        return models;
    }

    public void setModels(Map<String, Object> models) {
        this.models = models;
    }
}
