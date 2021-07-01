package com.smartfoxpro.secondapp.service;

import com.smartfoxpro.secondapp.entity.Event;

import java.util.List;

public interface EventService {

    void register(String publisherId);

    void subscribe(String consumerId, String publisherId);

    void send(Event event);

    List<Event> receive(String consumerId);
}
