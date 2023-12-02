package com.durys.jakub.recruitmentapp.invitation.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import static com.durys.jakub.recruitmentapp.invitation.domain.event.InvitationEvent.*;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;

import java.time.LocalDateTime;
import java.util.UUID;

public class Invitation extends AggregateRoot {



    public record Id(UUID value) {}

    enum State {
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
        this.state = State.Closed;

        addEvent(
           new InvitationAccepted(id.value, interviewId.value(), this.interviewTerm.value(), this.reviewerId.value())
        );
    }

    public void reject(String declineReason) {

        this.rejectionReason = new RejectionReason(declineReason);
        this.state = State.Closed;

        addEvent(
            new InvitationRejected(id.value, interviewId.value(), this.reviewerId.value())
        );
    }


    public Id id() {
        return id;
    }


    public State state() {
        return state;
    }
}
