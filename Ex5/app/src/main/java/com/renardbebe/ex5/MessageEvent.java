package com.renardbebe.ex5;

/**
 * Created by renardbebe on 2017/10/28.
 */

public class MessageEvent {
    private Data data;

    public MessageEvent(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }
}
