package com.vdudnyk.discountwallet.application.business.shared;

import com.vdudnyk.discountwallet.application.event.EventType;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class BusinessEventDTO {
    private LocalDateTime time;
    private EventType eventType;
    private String description;
}
