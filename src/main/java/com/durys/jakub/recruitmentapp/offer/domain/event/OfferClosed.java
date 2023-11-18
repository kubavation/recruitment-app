package com.durys.jakub.recruitmentapp.offer.domain.event;

import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public record OfferClosed(UUID id, Instant at, UUID offerId, LocalDateTime closedAt) implements DomainEvent {

    public OfferClosed(UUID offerId, LocalDateTime closedAt) {
        this(UUID.randomUUID(), Instant.now(), offerId, closedAt);
    }
}
