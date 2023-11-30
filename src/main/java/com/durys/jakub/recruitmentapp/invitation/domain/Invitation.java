package com.durys.jakub.recruitmentapp.invitation.domain;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import com.durys.jakub.recruitmentapp.commons.exception.ValidationException;
import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import com.durys.jakub.recruitmentapp.interview.domain.Interview;
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
    }

    public void changeTerm(LocalDateTime at) {

        var term = new Term(at);

        if (!availableTerms.dateValidWithAvailableTerms(term)) {
            throw new ValidationException("Chosen date not in range of available terms");
        }

        this.interviewTerm = term;
    }
}
