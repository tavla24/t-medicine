package com.milaev.medicine.board.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.inject.Inject;

//@ApplicationScoped
@Singleton
public class MessageSender {

    private static Logger log =  LoggerFactory.getLogger(MessageSender.class);

    @Inject
    private MQConnectionFactory connectionFactory;

    public void sendMessage(String text) {
        log.info("Sending {}", text);
        connectionFactory.sendMessage(text);
    }
}
