package com.durys.jakub.recruitmentapp.interview.domain;

import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;

class Invitation {

    private final Term at;
    private final ReviewerId reviewerId;

    Invitation(Term at, ReviewerId reviewerId) {
        this.at = at;
        this.reviewerId = reviewerId;
    }
}
