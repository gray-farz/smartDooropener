package com.example.remotemvp.model;

public class IMEIconnectedPHONES {

    private int id;
    private String IMEIthis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        //Log.d("aaa","id setted");
    }

    public String getIMEIthis() {
        return IMEIthis;
    }

    public void setIMEIthis(String IMEIthis) {
        this.IMEIthis = IMEIthis;
        //Log.d("aaa","IMEI setted");
    }

    public String toString() {
        return IMEIthis;
    }
}
