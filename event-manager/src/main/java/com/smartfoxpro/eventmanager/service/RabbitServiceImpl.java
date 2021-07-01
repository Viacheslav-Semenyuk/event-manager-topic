package com.smartfoxpro.eventmanager.service;

import com.smartfoxpro.eventmanager.entity.Event;
import com.smartfoxpro.eventmanager.exception.GlobalRestExceptionException;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RabbitServiceImpl implements RabbitService {
    private final String EXCHANGE_NAME = "exchange_";
    private final String QUEUE_NAME = "queue_";
    private final String ROUTING_KEY_All = "queue.*";

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Override
    public void register(String publisherId) {
        TopicExchange topicExchange = new TopicExchange(EXCHANGE_NAME + publisherId, false, false);
        rabbitAdmin.declareExchange(topicExchange);
    }

    @Override
    public void subscribe(String consumerId, String publisherId) {
        TopicExchange topicExchange = new TopicExchange(EXCHANGE_NAME + publisherId, false, false);
        Queue queue = new Queue(QUEUE_NAME + consumerId);
        rabbitAdmin.declareExchange(topicExchange);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY_All));
    }

    @Override
    public void send(Event event) {
        rabbitAdmin.getRabbitTemplate().convertAndSend(EXCHANGE_NAME + event.getPublisherId(), ROUTING_KEY_All, event);
    }

    @Override
    public List<Event> receive(String publisherId) {
        List<Event> eventList = new ArrayList<>();
        try {
            int count = Objects.requireNonNull(rabbitAdmin.getQueueInfo(QUEUE_NAME + publisherId))
                    .getMessageCount();
            for (int i = 0; i < count; i++) {
                eventList.add((Event) rabbitAdmin.getRabbitTemplate()
                        .receiveAndConvert(QUEUE_NAME + publisherId));
            }
        } catch (NullPointerException e) {
            throw new GlobalRestExceptionException("Queue not found");
        }
        return eventList;
    }
}
