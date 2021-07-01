package com.smartfoxpro.eventmanager.service;

import com.smartfoxpro.eventmanager.entity.Event;

import java.util.List;

public interface RabbitService {

    void register(String publisherId);

    void subscribe(String consumerId, String publisherId);

    void send(Event event);

    List<Event> receive(String consumerId);
}
