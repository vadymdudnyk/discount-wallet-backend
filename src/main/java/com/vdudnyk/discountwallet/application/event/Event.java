package com.vdudnyk.discountwallet.application.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Wither;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Wither
@EqualsAndHashCode
@AllArgsConstructor
public class Event implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String description;
    private String uuid;
    private Long userId;
    private Long couponId;
    private Long businessId;
    private Long campaignId;
    private Long notificationId;
    private String payload;
    private Class<?> payloadType;
    private LocalDateTime createdAt;

    protected Event() {
        this.uuid = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

}
