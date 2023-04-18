package com.durys.jakub.recruitmentapp.interview.domain;

import com.durys.jakub.recruitmentapp.registration.domain.RegistrationId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Interview {

    private final InterviewId interviewId;
    private final RegistrationId registrationId;

}
