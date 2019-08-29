package com.milaev.medicine.message;

import com.milaev.mq.MQDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class MessageReceiver implements MQDescription {

    static final Logger log = LoggerFactory.getLogger(MessageReceiver.class);

    @JmsListener(id = CONNECTION_NAME, destination = EVENT_QUEUE_RESPONSE)
    public void receiveMessage(final Message<String> message) throws JMSException {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        String response = message.getPayload();

        //String response = ((StateChangedResponse)message.getPayload()).getText();

        log.info(response);
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}