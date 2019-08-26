package com.milaev.medicine.message;

import javax.jms.JMSException;

import com.milaev.mq.MQDescription;
import com.milaev.mq.message.StateChangedResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver implements MQDescription {

    static final Logger log = LoggerFactory.getLogger(MessageReceiver.class);

    @JmsListener(destination = EVENT_QUEUE_RESPONSE)
    public void receiveMessage(final Message<String> message) throws JMSException {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        String response = message.getPayload();

        //String response = ((StateChangedResponse)message.getPayload()).getText();

        log.info(response);
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}