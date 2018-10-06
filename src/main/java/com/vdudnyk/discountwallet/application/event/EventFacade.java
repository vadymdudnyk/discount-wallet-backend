package com.vdudnyk.discountwallet.application.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventFacade {
    private final EventStore eventStore;

    public List<Event> findByBusinessId(Long businessId) {
        return eventStore.findByBusinessId(businessId);
    }
}
