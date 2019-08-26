package com.milaev.medicine.board.ejb;

import com.milaev.medicine.board.ws.BoardEndpointPeers;
import com.milaev.mq.message.StateChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;

@Stateless
public class Reader implements MessageListener {

    private static Logger log =  LoggerFactory.getLogger(Reader.class);

    @Inject
    BoardEndpointPeers peers;

    @Override
    public void onMessage(Message message) {
        try {
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();

//            ObjectMessage objectMessage = (ObjectMessage) message;
//            String text = ((StateChangedEvent) objectMessage.getObject()).getText();

            log.info(text);
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
            peers.sendMessage(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
