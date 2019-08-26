package com.milaev.mq.message;

import java.io.Serializable;

public class StateChangedResponse implements Serializable {

    private String text;

    public StateChangedResponse() {}

    public StateChangedResponse(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
