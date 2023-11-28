package com.durys.jakub.recruitmentapp.interview.domain;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.sharedkernel.AvailableTerm;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;
import com.durys.jakub.recruitmentapp.sharedkernel.TenantId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.durys.jakub.recruitmentapp.interview.domain.event.InterviewEvent.*;

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
    private List<AvailableTerm> availableTerms = new ArrayList<>();

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

    public void chooseAvailableTerms(List<AvailableTerm> availableTerms) {

        if (state != State.New) {
            throw new InvalidStateForOperationException("Cannot change available terms");
        }

        this.availableTerms = availableTerms;
        this.state = State.Waiting;
    }

    public void assignReviewer(ReviewerId reviewerId, LocalDateTime at) {

        if (state == State.Completed) {
            throw new InvalidStateForOperationException("Cannot assign reviewer");
        }

        if (!dateValidWithAvailableTerms(at)) {
            throw new ValidationException("Chosen date not in range of available terms");
        }

        this.review = new Review(reviewerId, at);
        this.state = State.Waiting;

        addEvent(
            new ReviewerAssigned(id.value, reviewerId.value(), at)
        );
    }

    public void acceptInvitation() {

        if (state != State.Waiting) {
            throw new InvalidStateForOperationException("Invitation cannot be accepted");
        }

        review.accept();
        state = State.Planned;

        addEvent(
            new InvitationAccepted(id.value)
        );
    }

    public void declineInvitation() {

        if (state != State.Waiting) {
            throw new InvalidStateForOperationException("Invitation cannot be declined");
        }

        review.decline();
        state = State.Waiting;

        addEvent(
            new InvitationDeclined(id.value)
        );
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

    boolean dateValidWithAvailableTerms(LocalDateTime at) {
        return availableTerms.stream()
                .anyMatch(term -> term.inRange(at));
    }

    public State state() {
        return state;
    }

    public ReviewerId reviewerId() {
        return review.reviewerId();
    }

}