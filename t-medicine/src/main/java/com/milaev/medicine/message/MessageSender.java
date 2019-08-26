package com.milaev.medicine.message;

import javax.jms.*;

import com.milaev.mq.message.StateChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.UncategorizedJmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    static final Logger log = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final String text) {
        try {
            jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage(text);
                    return textMessage;
                    
//                    ObjectMessage objectMessage = session.createObjectMessage(new StateChangedEvent(text));
//                    return objectMessage;
                }
            });
            log.info("Sending {}", text);
        } catch (UncategorizedJmsException ex) {
            log.error("Unable to send message. Exception: {}", ex.getStackTrace());
        }
    }

//    public void sendMessage(final String text) {
//        System.out.println("Sending " + text);
//        jmsTemplate.convertAndSend("spring-medicine-queue", text);
//    }

}