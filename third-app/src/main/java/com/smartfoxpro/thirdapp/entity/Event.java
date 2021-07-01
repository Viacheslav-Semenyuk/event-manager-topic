package com.smartfoxpro.thirdapp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Event {

    private String publisherId;

    private String topic;

    private String message;

    private Date date;
}
