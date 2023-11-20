package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.events.DomainEventRegistry;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationAccepted;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationDeclined;

import java.util.UUID;

public class Registration {

    public record Id(UUID value) {}

    public enum RegistrationStatus {
        Submitted, Rejected, Accepted
    }

    private final Id id;
    private final Offer.Id offerId;
    private final ApplicantInformation applicantInformation;
    private final Cv cv;
    private RegistrationStatus status;




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
