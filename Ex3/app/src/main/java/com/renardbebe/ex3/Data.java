package com.renardbebe.ex3;

/**
 * Created by renardbebe on 2017/10/22.
 */

import java.io.Serializable;

public class Data implements Serializable{
    private String name;
    private String price;
    private String type;
    private String info;
    private int cnt;

    public Data(String name, String price, String type, String info, int cnt) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.info = info;
        this.cnt = cnt;
    }

    public String getName() {
        return name;
    }
    public String getPrice() { return price; }
    public String getType() {
        return type;
    }
    public String getInfo() {
        return info;
    }
    public int getCnt() {return cnt;}
}
