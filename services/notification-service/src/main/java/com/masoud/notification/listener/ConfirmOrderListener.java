package com.masoud.notification.listener;


import com.masoud.notification.channels.ChannelInterface;
import com.masoud.notification.dto.ConfirmOrderDTO;
import com.masoud.notification.model.Notification;
import com.masoud.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ConfirmOrderListener {

    private final NotificationRepository notificationRepository;
    private final ChannelInterface channel;
    @KafkaListener(topics = "order",groupId = "${spring.kafka.groupId}")
    public void listenConfirmOrder (ConfirmOrderDTO confirmOrder) {

        Notification notification = Notification.builder()
                .channel(channel.getChannelName())
                .message("order confirmed")
                .build();

        notificationRepository.save(notification);

        channel.send(confirmOrder,"masoud.niki79@gmail.com");
    }
}
