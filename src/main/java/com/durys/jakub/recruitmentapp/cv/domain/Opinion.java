package com.durys.jakub.recruitmentapp.cv.domain;

record Opinion(ReviewerId reviewerId, String text, com.durys.jakub.recruitmentapp.cv.domain.Opinion.Status status) {

    public enum Status {
        APPROVED, DECLINED
    }

}
