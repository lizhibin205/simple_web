package com.bytrees.utils;

import com.alibaba.fastjson.JSON;

public class ResponseJson<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseJson(Integer code, String message, T data) {
    	this.code = code;
    	this.message = message;
    	this.data = data;
    }

    public Integer getCode() {
    	return code;
    }
    public String getMessage() {
    	return message;
    }
    public T getData() {
    	return data;
    }

    @Override
    public String toString() {
    	return JSON.toJSONString(this);
    }
}
