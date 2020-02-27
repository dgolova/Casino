package com.example.techconnect.BaccaratApp.classes;

public class Response {

    private String response;
    private boolean success;

    public Response(String response, boolean success) {
        this.response = response;
        this.success = success;
    }

    public Response(){

    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
