package com.durys.jakub.recruitmentapp.technicalinterview.domain;

import com.durys.jakub.recruitmentapp.registration.domain.RegistrationId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TechnicalInterview {

    private final TechnicalInterviewId technicalInterviewId;
    private final RegistrationId registrationId;

}
