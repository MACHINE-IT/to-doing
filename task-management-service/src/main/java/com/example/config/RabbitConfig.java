package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("notification-exchange");
    }

    @Bean
    public Queue userQueue() {
        return QueueBuilder.durable("user-queue").build();
    }

    @Bean
    public Queue acknowledgmentQueue() {
        return QueueBuilder.durable("acknowledgment-queue").build();
    }

    @Bean
    public Binding userQueryBinding() {
        return BindingBuilder.bind(userQueue()).to(topicExchange()).with("user.#");
    }

    @Bean
    public Binding acknowledgmentBinding() {
        return BindingBuilder.bind(userQueue()).to(topicExchange()).with("acknowledgment.#");
    }

}
