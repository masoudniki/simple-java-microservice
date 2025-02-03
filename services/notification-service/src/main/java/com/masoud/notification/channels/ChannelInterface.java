package com.masoud.notification.channels;

import com.masoud.notification.dto.ConfirmOrderDTO;

public interface ChannelInterface {
    void send(ConfirmOrderDTO confirmOrder,String to);
    String getChannelName();
    void setChannelName(String channelName);
}
