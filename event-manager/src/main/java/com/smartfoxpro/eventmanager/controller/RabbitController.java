package com.smartfoxpro.eventmanager.controller;

import com.smartfoxpro.eventmanager.entity.Event;
import com.smartfoxpro.eventmanager.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RabbitController {

    @Autowired
    private RabbitService rabbitService;

    @PostMapping("/register")
    public void register(@RequestParam String publisherId) {
        rabbitService.register(publisherId);
    }

    @PostMapping("/subscribe")
    public void subscribe(@RequestParam String consumerId, @RequestParam String publisherId) {
        rabbitService.subscribe(consumerId, publisherId);
    }

    @PostMapping("/send")
    public void send(@RequestBody Event event) {
        rabbitService.send(event);
    }

    @GetMapping("/receive")
    public List<Event> receive(@RequestParam String consumerId) {
        return rabbitService.receive(consumerId);
    }

}
