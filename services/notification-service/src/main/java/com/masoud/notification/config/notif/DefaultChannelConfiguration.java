package com.masoud.notification.config.notif;

import com.masoud.notification.channels.ChannelInterface;
import com.masoud.notification.channels.DefaultChannelNotification;
import com.masoud.notification.channels.EmailChannel;
import com.masoud.notification.channels.SMSChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;

@Configuration
public class DefaultChannelConfiguration {
    @Value(value = "${notification.default-channel.name}")
    private String defaultChannelName;
    @Value(value = "${notification.default-channel.class-path}")
    private String defaultChannelClassPath;


    @Bean
    public DefaultChannelNotification defaultChannelNotification() {
        return new DefaultChannelNotification(defaultChannelName);
    }

    @Bean
    public ChannelInterface defaultChannelInterface() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // first of all check the class exists
        Class<?> clazz = Class.forName(defaultChannelClassPath);
        // then create instance of the channel
        Object channel = clazz.getDeclaredConstructor().newInstance();

        return (ChannelInterface) channel;

    }




}
