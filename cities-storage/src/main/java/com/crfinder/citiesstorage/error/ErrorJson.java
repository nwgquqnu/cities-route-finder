package com.crfinder.citiesstorage.error;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorJson {

    private Integer status;
    private String error;

    @JsonIgnore
    private String message;
    private String timeStamp;
    private String trace;

    public ErrorJson() {}

    public ErrorJson(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.error = getAttribute(errorAttributes, "error");
        this.message = getAttribute(errorAttributes, "message");
        this.timeStamp = getAttribute(errorAttributes, "timestamp");
        this.trace = getAttribute(errorAttributes, "trace");
    }
    
    private String getAttribute(Map<String, Object> attrs, String attrName) {
        Object value = attrs.get(attrName);
        return value != null ? value.toString() : null;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

}
