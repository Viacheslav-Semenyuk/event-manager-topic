package com.smartfoxpro.thirdapp.service;

import com.smartfoxpro.thirdapp.entity.Event;

import java.util.List;

public interface EventService {

    void register(String publisherId);

    void subscribe(String consumerId, String publisherId);

    void send(Event event);

    List<Event> receive(String consumerId);
}
