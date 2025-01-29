package com.masoud.notification.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Document
@Builder
public class Notification {
    @Id
    private String Id;
    private String message;
    private LocalDateTime createdAt;
    private String channel;
}
