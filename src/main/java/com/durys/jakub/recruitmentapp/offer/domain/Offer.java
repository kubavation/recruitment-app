package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.offer.domain.event.OfferPublished;

import java.time.Instant;
import java.util.UUID;

public class Offer {

    public record Id(UUID value) { }

    public enum Status {
        New, Published, Closed
    }

    private final Id offerId;
    private final Position position;
    private final Description description;
    private final ApplicantLimit limit;
    private final OfferPeriod period;
    private Status state;

    Offer(Id offerId, Position position, Description description, ApplicantLimit limit, OfferPeriod period, Status state) {
        this.offerId = offerId;
        this.position = position;
        this.description = description;
        this.limit = limit;
        this.period = period;
        this.state = state;
    }

    Offer(Id offerId, Position position, Description description, ApplicantLimit limit, OfferPeriod period) {
        this(offerId, position, description, limit, period, Status.New);
    }

    public OfferPublished publish() {

        if (state == Status.Published) {
            throw new RuntimeException("Offer cannot be published");
        }

        this.state = Status.Published;

        return new OfferPublished(UUID.randomUUID(), Instant.now(), offerId.value);
    }

}
