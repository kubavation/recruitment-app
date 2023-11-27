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
        New, Waiting, Planned, Completed
    }

    private final Id id;
    private final Identifier identifier;
    private final Registration.Id registrationId;
    private final Offer.Id offerId;
    private final TenantId tenantId;
    private Review review;
    private State state;

    public Interview(Registration.Id registrationId, Offer.Id offerId, TenantId tenantId) {
        this.id = new Id(UUID.randomUUID());
        this.identifier = new Identifier(UUID.randomUUID());
        this.registrationId = registrationId;
        this.offerId = offerId;
        this.tenantId = tenantId;
        this.state = State.New;

        addEvent(
            new InterviewInitialized(this.id.value, this.registrationId.value(), this.tenantId.value())
        );
    }

    Interview(Id id, Identifier identifier, Registration.Id registrationId, Offer.Id offerId,
              TenantId tenantId, State state) {
        this.id = id;
        this.identifier = identifier;
        this.registrationId = registrationId;
        this.offerId = offerId;
        this.tenantId = tenantId;
        this.state = state;
    }

    public void assignReviewer(ReviewerId reviewerId, LocalDateTime at) {

        if (state == State.Completed) {
            throw new InvalidStateForOperationException("Cannot assign reviewer");
        }

        this.review = new Review(reviewerId, at);
        this.state = State.Waiting;

        addEvent(
            new ReviewerAssigned(id.value, reviewerId.value(), at)
        );
    }

    public void acceptInvitation() {

        if (state != State.Waiting) {
            throw new InvalidStateForOperationException("Interview already completed");
        }

        review.accept();
        state = State.Planned;
    }

    public void complete(String opinion, boolean acceptation) {

        if (state != State.Planned) {
            throw new InvalidStateForOperationException("Cannot complete interview");
        }

        review.complete(opinion, acceptation);
        state = State.Completed;

        addEvent(
            new InterviewCompleted(id.value, opinion, acceptation)
        );
    }



    public State state() {
        return state;
    }

}
