package com.digitalchina.aiyixuan.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class Result<T> {
    private int code;
    private String message;
    private Map<String, T> data;

    public Result(int code, String message, String key, T value) {
        this.code = code;
        this.message = message;
        this.data = new HashMap<>();
        this.data.put(key, value);
    }
}