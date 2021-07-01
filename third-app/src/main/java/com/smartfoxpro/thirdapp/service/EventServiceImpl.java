package com.smartfoxpro.thirdapp.service;

import com.smartfoxpro.thirdapp.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Value("${event-manager.host}")
    private String host;

    @Qualifier("event-manager-rest-template")
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void register(String publisherId) {
        String register_url = host + "register?publisherId={publisherId}";
        restTemplate.postForEntity(register_url, null, null, publisherId);
    }

    @Override
    public void subscribe(String consumerId, String publisherId) {
        String subscribe_url = host + "subscribe?consumerId={consumerId}&publisherId={publisherId}";
        restTemplate.postForEntity(subscribe_url, null, null, consumerId, publisherId);
    }

    @Override
    public void send(Event event) {
        String send_url = host + "send";
        event.setDate(new Date());
        restTemplate.postForEntity(send_url, event, Event.class);
    }

    @Override
    public List<Event> receive(String consumerId) {
        String receive_url = host + "receive?consumerId={consumerId}";
        ResponseEntity<List<Event>> response = restTemplate.exchange(receive_url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Event>>() {
                },
                consumerId);
        return response.getBody();
    }
}
