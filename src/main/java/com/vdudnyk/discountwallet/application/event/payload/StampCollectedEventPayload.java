package com.vdudnyk.discountwallet.application.event.payload;

import lombok.Value;

@Value
public class StampCollectedEventPayload {
    private Long stamps;
}
