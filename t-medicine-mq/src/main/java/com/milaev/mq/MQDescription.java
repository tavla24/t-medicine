package com.milaev.mq;

public interface MQDescription {

    String DEFAULT_BROKER_URL = "tcp://localhost:61616";
    String EVENT_QUEUE = "medicine.topic?consumer.retroactive=true";
    String EVENT_QUEUE_RESPONSE = "medicine.response.topic?consumer.retroactive=true";
    //String EVENT_QUEUE = "medicine.topic";
    String TRUSTED_PACKAGE = "com.t-medicine";
    String CONNECTION_NAME = "medicine-mq";

}
