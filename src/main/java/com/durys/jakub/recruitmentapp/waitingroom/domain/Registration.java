package com.durys.jakub.recruitmentapp.waitingroom.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Registration {

    private final RegistrationId id;
    private final ApplicantInformation applicantInformation;
    private final Cv cv;

}
