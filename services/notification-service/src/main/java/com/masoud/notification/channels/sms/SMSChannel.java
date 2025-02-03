package com.masoud.notification.channels.sms;

import com.masoud.notification.channels.ChannelInterface;
import com.masoud.notification.dto.ConfirmOrderDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class SMSChannel implements ChannelInterface {
    private String channelName;

    @Override
    public void send(ConfirmOrderDTO confirmOrder, String to) {

    }
}
