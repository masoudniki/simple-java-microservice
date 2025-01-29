package com.masoud.notification.service;

import com.masoud.notification.channels.DefaultChannelNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Notification {
    private final DefaultChannelNotification defaultChannelNotification;
}
