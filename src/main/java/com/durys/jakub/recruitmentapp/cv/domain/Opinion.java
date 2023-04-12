package com.durys.jakub.recruitmentapp.cv.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Opinion {

    public enum Status {
        APPROVED, DECLINED
    }

    private final ReviewerId reviewerId;
    private final String text;
}
