package com.masoud.notification.config.notif;

import com.masoud.notification.channels.ChannelInterface;
import com.masoud.notification.channels.DefaultChannelNotification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
    @Primary
    public ChannelInterface defaultChannelInterface(ApplicationContext applicationContext) throws ClassNotFoundException {
        // first of all check the class exists
        Class<?> clazz = Class.forName(defaultChannelClassPath);
        // Get the bean from the application context (Spring manages dependencies)
        ChannelInterface createdChannel = (ChannelInterface) applicationContext.getBean(clazz);

        createdChannel.setChannelName(defaultChannelName);

        return createdChannel;

    }




}
