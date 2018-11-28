package com.mihoyo.hk4e.wechat.constants;

import com.alibaba.fastjson.JSONObject;

public enum MsgType {

    TEXT("text");


    private String msgType;

    MsgType(String msgType){
        this.msgType = msgType;
    }

    public String getMsgType() {
        return msgType;
    }

    public JSONObject genJson(String content){
        JSONObject json = new JSONObject();
        json.put("content", content);
        return json;
    }
}
