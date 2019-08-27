package com.milaev.mq.message;

import java.io.Serializable;

public class StateChangedEvent implements Serializable {

    private String text;

    public StateChangedEvent() {}

    public StateChangedEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
