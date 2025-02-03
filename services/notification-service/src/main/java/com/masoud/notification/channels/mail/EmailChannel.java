package com.masoud.notification.channels.mail;

import com.masoud.notification.channels.ChannelInterface;
import com.masoud.notification.dto.ConfirmOrderDTO;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class EmailChannel implements ChannelInterface {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    private String channelName;
    @Override
    public void send(ConfirmOrderDTO confirmOrder,String to) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
            messageHelper.setFrom("masoud@niki.com");
            final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();
            Map<String,Object> variables = new HashMap<>();
            variables.put("customerId",confirmOrder.customerId());
            variables.put("amount",confirmOrder.price());
            variables.put("orderReference",confirmOrder.orderId());

            Context context = new Context();
            context.setVariables(variables);
            messageHelper.setText(EmailTemplates.ORDER_CONFIRMATION.getSubject());

            String htmlTemplate = templateEngine.process(templateName, context);

            templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);
            messageHelper.setTo(to);

            mailSender.send(mimeMessage);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }

}
