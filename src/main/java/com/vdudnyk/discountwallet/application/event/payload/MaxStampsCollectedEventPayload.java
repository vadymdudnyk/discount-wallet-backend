package com.vdudnyk.discountwallet.application.event.payload;

import lombok.Value;

@Value
public class MaxStampsCollectedEventPayload {
    private String activationCode;
}
