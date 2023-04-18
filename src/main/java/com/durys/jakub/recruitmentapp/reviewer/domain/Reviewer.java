package com.durys.jakub.recruitmentapp.reviewer.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Reviewer {

    private final ReviewerId reviewerId;
    private final String name;
    private final String email;
}
