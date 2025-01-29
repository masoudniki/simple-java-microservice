package com.masoud.notification.listener;


import org.springframework.kafka.annotation.KafkaListener;


public class ConfirmOrderListener {

    @KafkaListener(topics = "order",groupId = "${spring.kafka.groupId}")
    public void listenConfirmOrder (String message) {

    }

}
