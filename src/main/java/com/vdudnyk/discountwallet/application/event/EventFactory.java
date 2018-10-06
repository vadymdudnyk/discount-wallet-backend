package com.vdudnyk.discountwallet.application.event;

import com.google.gson.Gson;
import com.vdudnyk.discountwallet.application.event.payload.UserDetails;
import com.vdudnyk.discountwallet.application.event.payload.UserSubscribedToBusinessEventPayload;
import com.vdudnyk.discountwallet.application.user.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventFactory {
    private static Gson gson = new Gson();

    public static Event createUserRegisteredEvent(Long userId,
                                                  Role role) {
        return new Event()
                .withUserId(userId)
                .withEventType(EventType.USER_REGISTERED_EVENT)
                .withDescription("User registered with id ${userId}")
                .withPayload(gson.toJson(role))
                .withPayloadType(role.getClass());
    }

    public static Event createUserAuthenticatedEvent(
            Long userId,
            UserDetails userDetails) {
        return new Event()
                .withUserId(userId)
                .withEventType(EventType.USER_AUTHENTICATED_EVENT)
                .withDescription("User authenticated with id ${userId}")
                .withPayload(gson.toJson(userDetails))
                .withPayloadType(userDetails.getClass());
    }

    public static Event createUserSubscribedToBusinessEvent(Long userId,
                                                            Long businessId,
                                                            UserSubscribedToBusinessEventPayload userSubscribedToBusinessEventPayload) {
        return new Event()
                .withUserId(userId)
                .withBusinessId(businessId)
                .withEventType(EventType.USER_SUBSCRIBED_TO_BUSINESS)
                .withDescription("User ${userId} subscribed to business ${businessId}")
                .withPayload(gson.toJson(userSubscribedToBusinessEventPayload))
                .withPayloadType(userSubscribedToBusinessEventPayload.getClass());
    }

}
