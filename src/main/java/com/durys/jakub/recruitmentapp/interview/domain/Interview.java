package com.durys.jakub.recruitmentapp.interview.domain;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.sharedkernel.AvailableTerm;
import com.durys.jakub.recruitmentapp.reviewer.ReviewerId;
import com.durys.jakub.recruitmentapp.sharedkernel.TenantId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.durys.jakub.recruitmentapp.interview.domain.event.InterviewEvent.*;

public class Interview extends AggregateRoot {


    public record Id(UUID value) {}

    public enum State {
        New, Waiting, InvitationSent, Planned, Completed
    }

    private final Id id;
    private final Identifier identifier;
    private final Registration.Id registrationId;
    private final TenantId tenantId;
    private Term term;
    private Review review;
    private AvailableTerms availableTerms;
    private ReviewerId reviewerId;

    private State state;

    public Interview(Registration.Id registrationId, TenantId tenantId) {
        this.id = new Id(UUID.randomUUID());
        this.identifier = new Identifier(UUID.randomUUID());
        this.registrationId = registrationId;
        this.tenantId = tenantId;
        this.state = State.New;

        addEvent(
            new InterviewInitialized(this.id.value,
                    this.registrationId.value(), this.tenantId.value(), this.identifier.value().toString())
        );
    }

    Interview(Id id, Identifier identifier, Registration.Id registrationId,
              TenantId tenantId, State state) {
        this.id = id;
        this.identifier = identifier;
        this.registrationId = registrationId;
        this.tenantId = tenantId;
        this.state = state;
    }

    public void chooseAvailableTerms(List<AvailableTerm> availableTerms) {

        if (state != State.New) {
            throw new InvalidStateForOperationException("Cannot change available terms");
        }

        this.availableTerms = new AvailableTerms(availableTerms);
        this.state = State.Waiting;

        addEvent(
            new InterviewTermsChosen(id.value, availableTerms)
        );
    }

    public void assignReviewer(ReviewerId reviewerId, LocalDateTime at) {

        if (state != State.Waiting && state != State.InvitationSent) {
            throw new InvalidStateForOperationException("Cannot assign reviewer");
        }

        if (!availableTerms.dateValidWithAvailableTerms(at)) {
            throw new ValidationException("Chosen date not in range of available terms");
        }

        this.reviewerId = reviewerId;
        this.term = new Term(at);
        state = State.Planned;

        addEvent(
            new ReviewerAssigned(id.value, reviewerId.value(), at)
        );
    }

    public void sendInvitationTo(ReviewerId reviewerId) {

        if (state != State.Waiting && state != State.InvitationSent) {
            throw new InvalidStateForOperationException("Cannot assign reviewer");
        }

        state = State.InvitationSent;

        addEvent(
            new InvitationSent(id.value, reviewerId, availableTerms.terms())
        );
    }


    public void acceptInvitation(ReviewerId reviewerId, LocalDateTime term) {

        if (state != State.InvitationSent) {
            throw new InvalidStateForOperationException("Invitation cannot be accepted");
        }

        this.term = new Term(term);
        this.reviewerId = reviewerId;
        this.state = State.Planned;

        addEvent(
            new InvitationAccepted(id.value, reviewerId, term)
        );
    }

    public void declineInvitation() {

        if (state != State.InvitationSent) {
            throw new InvalidStateForOperationException("Invitation cannot be declined");
        }

        this.state = State.Waiting;

        addEvent(
            new InvitationDeclined(id.value)
        );
    }

    public void complete(String opinion, boolean acceptation) {

        if (state != State.Planned) {
            throw new InvalidStateForOperationException("Cannot complete interview");
        }

        this.review = new Review(opinion, acceptation);
        state = State.Completed;

        addEvent(
            new InterviewCompleted(id.value, opinion, acceptation)
        );
    }



    public State state() {
        return state;
    }

    public ReviewerId reviewerId() {
        return reviewerId;
    }

    public Id id() {
        return id;
    }


}
