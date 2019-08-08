package com.jms.springio.demo;

/**
 * Creator weishi8
 * Date&Time 2019-07-10 20:36
 * description
 */

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        System.out.println("Received <" + email + ">");
    }

}
