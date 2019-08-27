package com.milaev.medicine.board.todo;

import com.milaev.medicine.board.jms.MQConnectionFactory;
import com.milaev.mq.MQDescription;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;

@Stateless
public class MQConnectionFactoryV2 implements MQDescription {

    private static Logger log =  LoggerFactory.getLogger(MQConnectionFactory.class);

    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Topic topic;
    private TopicSubscriber subscriber;
    private MessageProducer producer;

    @Inject
    MessageListener listener;

    public void sendTextMessage(String text){
        try {
            producer.send(session.createTextMessage(text));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void createConnection(){
        log.info("MQConnectionFactory: @PostConstruct");
        connectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID(CONNECTION_NAME);
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            topic = session.createTopic(EVENT_QUEUE_RESPONSE);
            producer = session.createProducer(topic);

            Topic topicListener = session.createTopic(EVENT_QUEUE);
            subscriber = session.createDurableSubscriber(topicListener, CONNECTION_NAME);
            subscriber.setMessageListener(listener);

            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void closeConnection(){
        log.info("MQConnectionFactory: @PreDestroy");
        try {
            if (connection != null)
                session.close();
            if (connection != null)
                connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}