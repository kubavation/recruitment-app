package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.ddd.annotations.events.DomainEventRegistry;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationAccepted;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationDeclined;

public class Registration {

    private final RegistrationId id;
    private final OfferId offerId;
    private final ApplicantInformation applicantInformation;
    private final Cv cv;

    private RegistrationStatus status;

    public Registration(RegistrationId id, OfferId offerId, ApplicantInformation applicantInformation, Cv cv) {
        this.id = id;
        this.offerId = offerId;
        this.applicantInformation = applicantInformation;
        this.cv = cv;
        this.status = RegistrationStatus.NEW;
    }

    public void markAsRejected(String reason) {

        if (status != RegistrationStatus.NEW) {
            throw new RuntimeException("Invalid operation");
        }

        this.status = RegistrationStatus.REJECTED;

        DomainEventRegistry
                .instance()
                .publish(new RegistrationDeclined(id, offerId, applicantInformation, reason));
    }

    public void markAsAccepted() {

        if (status != RegistrationStatus.NEW) {
            throw new RuntimeException("Invalid operation");
        }

        this.status = RegistrationStatus.ACCEPTED;

        DomainEventRegistry
                .instance()
                .publish(new RegistrationAccepted(id, offerId, cv));

    }

    public String applicantEmail() {
        return applicantInformation.email();
    }


}
