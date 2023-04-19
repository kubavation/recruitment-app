package com.durys.jakub.recruitmentapp.technicalinterview.domain;

import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;

record Opinion(ReviewerId reviewerId, String opinion, Status status) {

    public enum Status {
        ACCEPTED, DECLINED
    }

}
