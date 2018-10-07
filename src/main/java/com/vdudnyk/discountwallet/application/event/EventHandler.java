package com.vdudnyk.discountwallet.application.event;

import com.google.gson.Gson;
import com.vdudnyk.discountwallet.application.event.payload.UserDetails;
import com.vdudnyk.discountwallet.application.loyaltycard.LoyaltyCardFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventHandler {
    private final EventStore eventStore;
    private final LoyaltyCardFacade loyaltyCardFacade;
    @StreamListener(target = EventProcessor.INPUT_CHANNEL,
                    condition = "headers['eventType']=='USER_REGISTERED_EVENT'")
    public void eventHandler1(Event event) {
        log.info("Event received: {}", event.getEventType());
    }

    @StreamListener(target = EventProcessor.INPUT_CHANNEL,
                    condition = "headers['eventType']=='USER_AUTHENTICATED_EVENT'")
    public void eventHandler2(Event event) {
        UserDetails userDetails = getUserDetails(event);
        log.info(userDetails.getEmail());
        log.info("Event received: {}", event.getEventType());
        Event eventById = eventStore.getEventById(event.getId());
        log.info("Event from db:{}", eventById.equals(event));
    }

    @StreamListener(target = EventProcessor.INPUT_CHANNEL,
                    condition = "headers['eventType']=='USER_SUBSCRIBED_TO_BUSINESS'")
    public void userSubscribedToBusinessEventHandler(Event event) {
        log.info("Giving business: {} loyalty card for user: {}", event.getBusinessId(), event.getUserId());
        loyaltyCardFacade.giveLoyaltyCardForUser(event.getBusinessId(), event.getUserId());
    }

    private UserDetails getUserDetails(Event event) {
        return new Gson().fromJson(event.getPayload(),
                                   event.getEventType().getPayloadType());
    }
}
