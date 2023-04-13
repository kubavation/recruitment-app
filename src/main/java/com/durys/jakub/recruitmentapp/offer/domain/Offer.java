package com.durys.jakub.recruitmentapp.offer.domain;

import lombok.Getter;

@Getter
public class Offer {

    public enum Status {
        NEW, ACTIVE, INACTIVE
    }

    private final OfferId offerId;
    private final Position position;
    private final Description description;
    private final ApplicantLimit limit;
    private final OfferPeriod period;
    private Status status;

    public Offer(OfferId offerId, Position position, Description description, ApplicantLimit limit, OfferPeriod period) {
        this.offerId = offerId;
        this.position = position;
        this.description = description;
        this.limit = limit;
        this.period = period;
        this.status = Status.NEW;
    }

    public Offer(OfferId offerId, Position position, Description description, ApplicantLimit limit, OfferPeriod period, Status status) {
        this.offerId = offerId;
        this.position = position;
        this.description = description;
        this.limit = limit;
        this.period = period;
        this.status = status;
    }

    public void activate() {
        this.status = Status.ACTIVE;
    }

    public void deactivate() {
        this.status = Status.INACTIVE;
    }
}
