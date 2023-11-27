package com.durys.jakub.recruitmentapp.interview;

import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;

import java.time.LocalDateTime;

class Review {

    enum State {
        Waiting, Accepted, Declined
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

    void complete(String opinion, boolean acceptation) {
       reply = new Reply(opinion, acceptation);
    }
}
