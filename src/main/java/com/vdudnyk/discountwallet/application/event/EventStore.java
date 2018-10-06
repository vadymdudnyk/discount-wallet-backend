package com.vdudnyk.discountwallet.application.event;

import com.vdudnyk.discountwallet.application.shared.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class EventStore {
    private final EventRepository eventRepository;

    Event persistEvent(Event event) {
        return eventRepository.save(event);
    }

    Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ApiException("Event not found"));
    }

    List<Event> findByBusinessId(Long businessId) {
        return eventRepository.findAllByBusinessId(businessId);
    }
}
