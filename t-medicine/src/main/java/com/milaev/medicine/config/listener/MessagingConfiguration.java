package com.milaev.medicine.config.listener;

import com.milaev.mq.MQDescriptionDep;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;

import java.util.Arrays;

@Configuration
@PropertySource("classpath:mq/mq.properties")
public class MessagingConfiguration {

    //@Autowired
    //MQProperties mqProperties;
    @Autowired
    private Environment env;

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(env.getProperty("default.broker.url"));
        connectionFactory.setTrustedPackages(Arrays.asList(env.getProperty("trusted.package")));
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(env.getProperty("event.queue"));
        template.setPubSubDomain(true);
        return template;
    }
}
