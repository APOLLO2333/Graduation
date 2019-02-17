package com.supersong.graduation.utils;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    private int code = 200;
    private boolean success = true;
    private String message;
    private Map<String,Object> map = new HashMap<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Msg addData(String key, Object value){
        map.put(key,value);
        return this;
    }
    public static Msg success(){
        Msg msg = new Msg();
        msg.setMessage("操作成功!");
        return msg;
    }
    public static Msg fail(){
        Msg msg = new Msg();
        msg.setCode(500);
        msg.setSuccess(false);
        msg.setMessage("操作失败!");
        return msg;
    }
    public Msg addMessage(String message){
        this.setMessage(message);
        return this;
    }
}
