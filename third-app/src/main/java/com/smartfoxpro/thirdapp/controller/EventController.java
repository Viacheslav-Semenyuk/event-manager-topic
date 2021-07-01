package com.smartfoxpro.thirdapp.controller;

import com.smartfoxpro.thirdapp.entity.Event;
import com.smartfoxpro.thirdapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/register")
    public void register(@RequestParam String publisherId) {
        eventService.register(publisherId);
    }

    @PostMapping("/subscribe")
    public void subscribe(@RequestParam String consumerId, @RequestParam String publisherId) {
        eventService.subscribe(consumerId, publisherId);
    }

    @PostMapping("/send")
    public void send(@RequestBody Event event) {
        eventService.send(event);
    }

    @GetMapping("/receive")
    public List<Event> receive(@RequestParam String consumerId) {
        return eventService.receive(consumerId);
    }

}
