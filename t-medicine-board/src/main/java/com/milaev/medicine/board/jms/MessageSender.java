package com.milaev.medicine.board.jms;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
