package com.yunwltn98.firebasetest.model;

public class Chatmodel {

    String uid;
    String msg;
    String name;

    public Chatmodel() {
    }

    public Chatmodel(String uid, String msg) {
        this.uid = uid;
        this.msg = msg;
    }

    public Chatmodel(String uid, String msg, String name) {
        this.uid = uid;
        this.msg = msg;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
