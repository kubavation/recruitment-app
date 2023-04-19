package com.durys.jakub.recruitmentapp.technicalinterview.domain;

import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;

record Opinion(ReviewerId reviewerId, String opinion) {

    public enum Status {
        ACCEPTED, DECLINED
    }

}
