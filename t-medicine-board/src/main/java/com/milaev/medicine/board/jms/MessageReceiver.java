package com.milaev.medicine.board.jms;

import com.milaev.medicine.board.bean.BoardUpdater;
import com.milaev.medicine.board.ws.BoardEndpointPeers;
import com.milaev.mq.message.StateChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;

@Stateless
public class MessageReceiver implements MessageListener {

    private static Logger log =  LoggerFactory.getLogger(MessageReceiver.class);

    @Inject
    BoardEndpointPeers peers;

    @Inject
    BoardUpdater boardUpdater;

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
            boardUpdater.setNewData(true);
            peers.sendMessage(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
