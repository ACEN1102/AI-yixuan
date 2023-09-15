package com.digitalchina.aiyixuan.model;

import lombok.Data;

@Data
public class ApiResponse {
    private Object data;

    public ApiResponse(Object data) {
        this.data = data;
    }
}