package com.durys.jakub.recruitmentapp.interview.domain;

import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;

import java.time.LocalDateTime;

class Review {

    enum State {
        Waiting, Accepted, Declined, Completed
    }

    private ReviewerId reviewerId;
    private LocalDateTime at;
    private Reply reply;
    private State state;

    Review(ReviewerId reviewerId, LocalDateTime at) {
        this.reviewerId = reviewerId;
        this.at = at;
        this.state = State.Waiting;
    }

    void acceptInvitation() {
        state = State.Accepted;
    }

    void declineInvitation() {
        reviewerId = null;
        state = State.Declined;
    }

    void complete(String opinion, boolean acceptation) {
       reply = new Reply(opinion, acceptation);
       state = State.Completed;
    }

    ReviewerId reviewerId() {
        return reviewerId;
    }
}
