package com.durys.jakub.recruitmentapp.invitation.domain;

import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import static com.durys.jakub.recruitmentapp.invitation.domain.event.InvitationEvent.*;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;

import java.time.LocalDateTime;
import java.util.UUID;

public class Invitation extends AggregateRoot {

    record Id(UUID value) {}

    enum State {
        New, Accepted, Declined, Closed
    }

    private final Id id;
    private final Interview.Id interviewId;
    private final ReviewerId reviewerId;
    private final AvailableTerms availableTerms;

    private Term interviewTerm;
    private State state;

    Invitation(Id id, Interview.Id interviewId, ReviewerId reviewerId, AvailableTerms availableTerms, State state) {
        this.id = id;
        this.interviewId = interviewId;
        this.reviewerId = reviewerId;
        this.availableTerms = availableTerms;
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
        
        this.state = State.Accepted;

        addEvent(
           new InvitationAccepted(id.value, interviewId.value(), this.interviewTerm.value(), this.reviewerId.value())
        );
    }

    public void decline() {
        this.state = State.Declined;

        addEvent(
            new InvitationDeclined(id.value, interviewId.value(), this.reviewerId.value())
        );
    }
}
