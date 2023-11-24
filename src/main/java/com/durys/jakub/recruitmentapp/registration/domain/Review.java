package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;

class Review {
    private final ReviewerId reviewerId;
    private final String opinion;

    Review(ReviewerId reviewerId, String opinion) {
        this.reviewerId = reviewerId;
        this.opinion = opinion;
    }
}
