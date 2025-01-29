package com.masoud.notification.channels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailChannel implements ChannelInterface {
    private String channelName;
    @Override
    public void send() {

    }
}
