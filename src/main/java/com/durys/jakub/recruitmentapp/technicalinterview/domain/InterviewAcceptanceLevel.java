package com.durys.jakub.recruitmentapp.technicalinterview.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum InterviewAcceptanceLevel {
    ONE_ACCEPTATION(1),
    TWO_ACCEPTATIONS(2);

    private final Integer val;

    Integer val() {
        return val;
    }

}
