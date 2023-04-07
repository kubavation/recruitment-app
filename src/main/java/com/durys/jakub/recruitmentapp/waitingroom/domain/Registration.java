package com.durys.jakub.recruitmentapp.waitingroom.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Registration {

    private final RegistrationId id;
    private final OfferId offerId;
    private final ApplicantInformation applicantInformation;
    private final Cv cv;

    private RegistrationStatus status;

    public void markAsDeclined() {
        this.status = RegistrationStatus.DECLINED;
    }

    public void markAsAccepted() {
        this.status = RegistrationStatus.ACCEPTED;
    }

}
