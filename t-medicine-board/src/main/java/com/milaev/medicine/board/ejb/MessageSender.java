package com.milaev.medicine.board.ejb;

import com.milaev.medicine.board.connection.MQConnectionFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@ApplicationScoped
@Stateless
public class MessageSender {

    private static Logger log =  LoggerFactory.getLogger(MessageSender.class);

    @Inject
    private MQConnectionFactory connectionFactory;

    public void sendMessage(String text) {
        log.info("Sending {}", text);
        connectionFactory.sendMessage(text);
    }
}
