package com.durys.jakub.recruitmentapp.interview;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;
import com.durys.jakub.recruitmentapp.sharedkernel.TenantId;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.durys.jakub.recruitmentapp.interview.event.InterviewEvent.*;

public class Interview extends AggregateRoot {

    public record Id(UUID value) {}

    public enum State {
        NEW, PLANNED, COMPLETED
    }

    private final Id id;
    private final Registration.Id registrationId;
    private final Offer.Id offerId;
    private final TenantId tenantId;
    private Review review;
    private State state;

    public Interview(Registration.Id registrationId, Offer.Id offerId, TenantId tenantId) {
        this.id = new Id(UUID.randomUUID());
        this.registrationId = registrationId;
        this.offerId = offerId;
        this.tenantId = tenantId;
        this.state = State.NEW;

        addEvent(
            new InterviewInitialized(this.id.value, this.registrationId.value(), this.tenantId.value())
        );
    }

    Interview(Id id, Registration.Id registrationId, Offer.Id offerId,
              TenantId tenantId, ReviewerId reviewerId, State state) {
        this.id = id;
        this.registrationId = registrationId;
        this.offerId = offerId;
        this.tenantId = tenantId;
        this.state = state;
    }

    public void assignReviewer(ReviewerId reviewerId, LocalDateTime at) {

        if (state == State.COMPLETED) {
            throw new InvalidStateForOperationException("Cannot assign reviewer");
        }

        this.review = new Review(reviewerId, at);
        this.state = State.PLANNED;

        addEvent(
            new ReviewerAssigned(id.value, reviewerId.value(), at)
        );
    }

    public void complete(String opinion, boolean acceptation) {

        if (state != State.PLANNED) {
            throw new InvalidStateForOperationException("Cannot complete interview");
        }

        review.complete(opinion, acceptation);
        state = State.COMPLETED;

        addEvent(
            new InterviewCompleted(id.value, opinion, acceptation)
        );
    }



    public State state() {
        return state;
    }

}
