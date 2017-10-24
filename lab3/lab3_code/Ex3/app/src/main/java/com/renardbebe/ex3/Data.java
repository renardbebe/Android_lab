package com.renardbebe.ex3;

/**
 * Created by renardbebe on 2017/10/22.
 */

public class Data {
    private String name;
    private String price;
    private String type;
    private String info;

    public Data(String name, String price, String type, String info) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.info = info;
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
}
