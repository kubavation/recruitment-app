package com.durys.jakub.recruitmentapp.technicalinterview.domain;

import com.durys.jakub.recruitmentapp.registration.domain.RegistrationId;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class TechnicalInterview {

    private final TechnicalInterviewId technicalInterviewId;
    private final RegistrationId registrationId;
    private final InterviewAcceptanceLevel acceptanceLevel;
    private Set<Opinion> opinions = new HashSet<>();

}
