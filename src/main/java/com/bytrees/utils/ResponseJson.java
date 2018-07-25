package com.bytrees.utils;

public class ResponseJson<T> {
    private int response;
    private String message;
    private T data;

    public ResponseJson(int response, String message, T data) {
    	this.response = response;
    	this.message = message;
    	this.data = data;
    }

    public int getResponse() {
    	return response;
    }
    public String getMessage() {
    	if (message == null) {
    		return "no message";
    	} else {
    		return message;
    	}
    }
    public T getData() {
    	return data;
    }
}
