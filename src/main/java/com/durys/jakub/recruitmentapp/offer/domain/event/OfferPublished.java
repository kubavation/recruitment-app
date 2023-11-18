package com.durys.jakub.recruitmentapp.offer.domain.event;

import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record OfferPublished(UUID id, Instant at, UUID offerId) implements DomainEvent {

    public OfferPublished(UUID offerId) {
        this(UUID.randomUUID(), Instant.now(), offerId);
    }
}
