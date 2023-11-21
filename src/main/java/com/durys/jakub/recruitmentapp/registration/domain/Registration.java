package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;

import java.util.UUID;

public class Registration extends AggregateRoot {


    public record Id(UUID value) {}

    public enum RegistrationStatus {
        Submitted, Rejected, Accepted
    }

    private final Id id;
    private final Offer.Id offerId;
    private final ApplicantInformation applicantInformation;
    private final Cv cv;
    private RegistrationStatus status;

    Registration(Id id, Offer.Id offerId, ApplicantInformation applicantInformation, Cv cv, RegistrationStatus status) {
        this.id = id;
        this.offerId = offerId;
        this.applicantInformation = applicantInformation;
        this.cv = cv;
        this.status = status;
    }

    Registration(Offer.Id offerId, ApplicantInformation applicantInformation, Cv cv) {
        this.id = new Id(UUID.randomUUID());
        this.offerId = offerId;
        this.applicantInformation = applicantInformation;
        this.cv = cv;
        this.status = RegistrationStatus.Submitted;
    }

//    public void markAsRejected(String reason) {
//
//        if (status != RegistrationStatus.NEW) {
//            throw new RuntimeException("Invalid operation");
//        }
//
//        this.status = RegistrationStatus.REJECTED;
//
//        DomainEventRegistry
//                .instance()
//                .publish(new RegistrationDeclined(id, offerId, applicantInformation, reason));
//    }
//
//    public void markAsAccepted() {
//
//        if (status != RegistrationStatus.NEW) {
//            throw new RuntimeException("Invalid operation");
//        }
//
//        this.status = RegistrationStatus.ACCEPTED;
//
//        DomainEventRegistry
//                .instance()
//                .publish(new RegistrationAccepted(id, offerId, cv));
//
//    }

    public String applicantEmail() {
        return applicantInformation.email();
    }


    public Id id() {
        return id;
    }

}
