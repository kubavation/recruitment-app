package com.durys.jakub.recruitmentapp.waitingroom.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Registration {

    private final RegistrationId id;
    private final OfferId offerId;
    private final ApplicantInformation applicantInformation;
    private RegistrationStatus status;
    private final Cv cv;

}
