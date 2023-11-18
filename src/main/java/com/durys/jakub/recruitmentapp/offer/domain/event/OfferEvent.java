package com.durys.jakub.recruitmentapp.offer.domain.event;

import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public sealed interface OfferEvent extends DomainEvent {

    record OfferClosed(UUID id, Instant at, UUID offerId, LocalDateTime closedAt) implements OfferEvent {

        public OfferClosed(UUID offerId, LocalDateTime closedAt) {
            this(UUID.randomUUID(), Instant.now(), offerId, closedAt);
        }
    }


    record OfferPublished(UUID id, Instant at, UUID offerId) implements OfferEvent {

        public OfferPublished(UUID offerId) {
            this(UUID.randomUUID(), Instant.now(), offerId);
        }
    }


}
