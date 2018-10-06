package com.vdudnyk.discountwallet.application.event;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventSender {

    private final EventProcessor eventPublisher;
    private final EventStore eventStore;

    public void send(Event event) {
        eventPublisher.output()
                      .send(MessageBuilder
                                    .withPayload(eventStore.persistEvent(event))
                                    .setHeader("eventType", event.getEventType())
                                    .setHeader("eventClass", event.getClass())
                                    .build());
    }
}
