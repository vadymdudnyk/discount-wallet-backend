package com.vdudnyk.discountwallet.application.event.payload;

import lombok.Value;

@Value
public class UserSubscribedToBusinessEventPayload {
    private String email;
    private String phoneNumber;
}
