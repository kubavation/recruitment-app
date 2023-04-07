package com.durys.jakub.recruitmentapp.waitingroom.domain;

import com.durys.jakub.recruitmentapp.ddd.annotations.events.DomainEventRegistry;
import com.durys.jakub.recruitmentapp.waitingroom.domain.events.RegistrationAccepted;
import com.durys.jakub.recruitmentapp.waitingroom.domain.events.RegistrationDeclined;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Registration {

    private final RegistrationId id;
    private final OfferId offerId;
    private final ApplicantInformation applicantInformation;
    private final Cv cv;

    private RegistrationStatus status;

    public void markAsDeclined(String reason) {
        this.status = RegistrationStatus.DECLINED;
        DomainEventRegistry.instance()
                .emit(new RegistrationDeclined(id, offerId, applicantInformation, reason));
    }

    public void markAsAccepted() {
        this.status = RegistrationStatus.ACCEPTED;
        DomainEventRegistry.instance()
                .emit(new RegistrationAccepted(id, offerId, cv));
    }

}
