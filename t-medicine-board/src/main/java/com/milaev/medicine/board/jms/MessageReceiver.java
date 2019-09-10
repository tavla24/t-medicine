package com.milaev.medicine.board.jms;

import com.milaev.medicine.board.bean.BoardUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.*;

@Singleton
public class MessageReceiver implements MessageListener {

    private static Logger log =  LoggerFactory.getLogger(MessageReceiver.class);

    @Inject
    BoardUpdater boardUpdater;

    @Override
    public void onMessage(Message message) {
        try {
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();

            log.info(text);
            log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
            boardUpdater.setNewState(true, text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
