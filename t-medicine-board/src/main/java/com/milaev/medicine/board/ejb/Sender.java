package com.milaev.medicine.board.ejb;

import com.milaev.medicine.board.connection.MQConnectionFactory;
import com.milaev.mq.message.StateChangedResponse;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@ApplicationScoped
@Stateless
public class Sender {

    private static Logger log =  LoggerFactory.getLogger(Sender.class);

    @Inject
    private MQConnectionFactory connectionFactory;

    public void sendMessage(String text) {
        log.info("Sending {}", text);
        connectionFactory.sendMessage(text);
    }
}
