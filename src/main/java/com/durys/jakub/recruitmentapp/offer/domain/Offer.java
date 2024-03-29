package com.durys.jakub.recruitmentapp.offer.domain;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import static com.durys.jakub.recruitmentapp.offer.domain.event.OfferEvent.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Offer extends AggregateRoot {

    public record Id(UUID value) {}

    public enum Status {
        New, Published, Closed
    }

    private final Id offerId;
    private final Position position;
    private final Description description;
    private final ApplicantLimit limit;
    private final OfferPeriod period;
    private OfferConfiguration configuration;
    private Status state;

    public Offer(Id offerId, Position position, Description description, ApplicantLimit limit, OfferPeriod period, Status state) {
        this.offerId = offerId;
        this.position = position;
        this.description = description;
        this.limit = limit;
        this.period = period;
        this.state = state;
    }

    Offer(Position position, Description description, ApplicantLimit limit, OfferPeriod period) {

        this(new Offer.Id(UUID.randomUUID()), position, description, limit, period, Status.New);

        addEvent(
                new OfferAdded(
                        id().value(), position(), description(),
                        limit(), from(), to())
        );
    }

    public void publish() {

        if (state == Status.Published) {
            throw new InvalidStateForOperationException("Offer cannot be published");
        }

        this.state = Status.Published;

        addEvent(new OfferPublished(UUID.randomUUID(), Instant.now(), offerId.value));
    }


    public void close(LocalDateTime closedAt) {

        if (state == Status.Closed) {
            throw new InvalidStateForOperationException("Offer cannot be closed");
        }

        this.state = Status.Closed;
        addEvent(new OfferClosed(UUID.randomUUID(), Instant.now(), offerId.value, closedAt));
    }

    public void changeConfiguration(Integer numberOfRequiredReviews, Integer numberOfTechnicalInterviews,
                                    Integer numberOfBusinessInterviews) {

        if (state != Status.New) {
            throw new InvalidStateForOperationException("Cannot change offer configuration");
        }

        this.configuration = new OfferConfiguration(numberOfRequiredReviews, numberOfTechnicalInterviews, numberOfBusinessInterviews);
    }


    public boolean isClosed() {
        return state == Status.Closed; //todo limit
    }

    public Id id() {
        return offerId;
    }

    public Status state() {
        return state;
    }

    public String position() {
        return position.name();
    }

    public String description() {
        return description.value();
    }

    public Integer limit() {
        return limit.value();
    }

    public LocalDate from() {
        return period.from();
    }

    public LocalDate to() {
        return period.to();
    }
}
