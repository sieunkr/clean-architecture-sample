package com.example.demo.entry.amqp;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageContent implements Serializable {

    private String query;
}
