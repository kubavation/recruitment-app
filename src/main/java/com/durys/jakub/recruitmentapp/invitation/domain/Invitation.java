package com.durys.jakub.recruitmentapp.invitation.domain;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import static com.durys.jakub.recruitmentapp.invitation.domain.event.InvitationEvent.*;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;

import java.time.LocalDateTime;
import java.util.UUID;

public class Invitation extends AggregateRoot {


    public record Id(UUID value) {}

    public enum State {
        New, Closed
    }

    private final Id id;
    private final Interview.Id interviewId;
    private final ReviewerId reviewerId;
    private final AvailableTerms availableTerms;

    private Term interviewTerm;
    private RejectionReason rejectionReason;

    private State state;

    Invitation(Id id, Interview.Id interviewId, ReviewerId reviewerId, AvailableTerms availableTerms,
               Term interviewTerm, RejectionReason rejectionReason, State state) {
        this.id = id;
        this.interviewId = interviewId;
        this.reviewerId = reviewerId;
        this.availableTerms = availableTerms;
        this.interviewTerm = interviewTerm;
        this.rejectionReason = rejectionReason;
        this.state = state;
    }

    public Invitation(Interview.Id interviewId, ReviewerId reviewerId, AvailableTerms availableTerms) {
        this.id = new Id(UUID.randomUUID());
        this.interviewId = interviewId;
        this.reviewerId = reviewerId;
        this.availableTerms = availableTerms;
        this.state = State.New;

        addEvent(
            new InvitationReceived(this.id.value, this.interviewId.value(), this.reviewerId.value(), this.availableTerms.terms())
        );
    }


    public void accept(LocalDateTime at) {

        var term = new Term(at);

        if (!availableTerms.dateValidWithAvailableTerms(term)) {
            throw new ValidationException("Chosen date not in range of available terms");
        }

        this.interviewTerm = term;
        close();

        addEvent(
           new InvitationAccepted(id.value, interviewId.value(), this.interviewTerm.value(), this.reviewerId.value())
        );
    }

    public void reject(String rejectionReason) {

        this.rejectionReason = new RejectionReason(rejectionReason);
        close();

        addEvent(
            new InvitationRejected(id.value, interviewId.value(), this.reviewerId.value(), rejectionReason)
        );
    }

    public void close() {

        if (this.state == State.Closed) {
            throw new InvalidStateForOperationException("Cannot close invitation");
        }

        this.state = State.Closed;
    }


    public Id id() {
        return id;
    }


    public State state() {
        return state;
    }

    public Interview.Id interviewId() {
        return interviewId;
    }
}
