package com.milaev.medicine.board.jms;

import com.milaev.medicine.board.utils.ResProperties;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.*;
import java.io.IOException;
import java.util.Properties;

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
            producer.send(session.createTextMessage(text));
        } catch (JMSException e) {
            log.error("Unable to send message. (JMSException)");
            closeConnection();
        }
    }

    @Schedule(second="*/5", minute="*", hour="*", persistent = false)
    public void createConnection(){
        if (connectionFactory != null)
            return;

        log.info("Create MQConnectionFactory");

        Properties prop = null;
        try {
            prop = (new ResProperties()).getProperties("mq.properties");
        } catch (IOException e) {
            log.error("Unable to read property file", e);
            return;
        }

        connectionFactory = new ActiveMQConnectionFactory(prop.getProperty("default.broker.url"));
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID(prop.getProperty("connection.name"));
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            topic = session.createTopic(prop.getProperty("event.queue.responce"));
            producer = session.createProducer(topic);

            Topic topicListener = session.createTopic(prop.getProperty("event.queue"));
            subscriber = session.createDurableSubscriber(topicListener, prop.getProperty("connection.name"));
            subscriber.setMessageListener(listener);

            connection.start();
        } catch (JMSException e) {
            log.error("Unable to create connection. (JMSException)");
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
            log.error("Unable to close connection.", e);
        }
    }
}
