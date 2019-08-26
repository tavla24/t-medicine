package com.milaev.medicine.board.connection;

import com.milaev.mq.MQDescription;
import com.milaev.mq.message.StateChangedResponse;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Schedule;
import javax.inject.Inject;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.jms.*;

@Startup
@Singleton
public class MQConnectionFactory {

    private static Logger log =  LoggerFactory.getLogger(MQConnectionFactory.class);

    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Topic topic;
    private TopicSubscriber subscriber;
    private MessageProducer producer;

    @Inject
    MessageListener listener;

    public void sendMessage(String text){
        try {
            //producer.send(session.createObjectMessage(new StateChangedResponse(text)));
            producer.send(session.createTextMessage(text));
        } catch (JMSException e) {
            log.error("Unable to send message. Exception: {}", e.getStackTrace());
            closeConnection();
        }
    }

    //@PostConstruct
    @Schedule(second="*", minute="*", hour="*")
    public void createConnection(){
        if (connectionFactory != null)
            return;

        log.info("Create MQConnectionFactory");
        connectionFactory = new ActiveMQConnectionFactory(MQDescription.DEFAULT_BROKER_URL);
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID(MQDescription.CONNECTION_NAME);
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            topic = session.createTopic(MQDescription.EVENT_QUEUE_RESPONSE);
            producer = session.createProducer(topic);

            Topic topicListener = session.createTopic(MQDescription.EVENT_QUEUE);
            subscriber = session.createDurableSubscriber(topicListener, MQDescription.CONNECTION_NAME);
            subscriber.setMessageListener(listener);

            connection.start();
        } catch (JMSException e) {
            log.error("Unable to create connection. Exception: {}", e.getStackTrace());
            closeConnection();
        }
    }

    @PreDestroy
    public void closeConnection(){
        log.info("Close connections");
        connectionFactory = null;
        try {
            if (connection != null)
                session.close();
            if (connection != null)
                connection.close();
        } catch (JMSException e) {
            log.error("Unable to close connection. Exception: {}", e.getStackTrace());
        }
    }
}
