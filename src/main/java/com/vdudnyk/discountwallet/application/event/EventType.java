package com.vdudnyk.discountwallet.application.event;

import com.vdudnyk.discountwallet.application.event.payload.MaxStampsCollectedEventPayload;
import com.vdudnyk.discountwallet.application.event.payload.StampCollectedEventPayload;
import com.vdudnyk.discountwallet.application.event.payload.UserDetails;
import com.vdudnyk.discountwallet.application.event.payload.UserSubscribedToBusinessEventPayload;
import com.vdudnyk.discountwallet.application.user.Role;

import java.lang.reflect.Type;

public enum EventType {

    USER_REGISTERED_EVENT(Role.class),
    USER_AUTHENTICATED_EVENT(UserDetails.class),
    USER_SUBSCRIBED_TO_BUSINESS(UserSubscribedToBusinessEventPayload.class),
    MAX_STAMPS_COLLECTED(MaxStampsCollectedEventPayload.class),
    STAMP_COLLECTED(StampCollectedEventPayload.class);

    private final Type payloadType;

    EventType(Type payloadType) {
        this.payloadType = payloadType;
    }

    Type getPayloadType() {
        return payloadType;
    }
}
