package com.anupam.pojo;

public class AppResponse {

    private String status;
    private Object data;

    public static AppResponse success(Object data) {
        AppResponse response = new AppResponse();
        response.status = "success";
        response.data = data;
        return response;
    }

    public static AppResponse failure(Object data) {
        AppResponse response = new AppResponse();
        response.status = "fail";
        response.data = data;
        return response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
