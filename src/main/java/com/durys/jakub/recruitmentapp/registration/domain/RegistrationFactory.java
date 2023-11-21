package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.offer.domain.Offer;

import java.util.UUID;

public class RegistrationFactory {


    public static Registration create(UUID offerId, String applicantFirstName, String applicantLastName,
                                      String email, String phoneNumber, String fileName, byte[] file) {

        return new Registration(
                new Offer.Id(offerId),
                new ApplicantInformation(applicantFirstName, applicantLastName, email, phoneNumber),
                new Cv(fileName, file)
        );
    }

    public static Registration create(UUID id, UUID offerId, String applicantFirstName, String applicantLastName,
                                      String email, String phoneNumber, String fileName,
                                      byte[] file, String rejectionReason, String state) {

        return new Registration(
                new Registration.Id(id),
                new Offer.Id(offerId),
                new ApplicantInformation(applicantFirstName, applicantLastName, email, phoneNumber),
                new Cv(fileName, file),
                rejectionReason.isEmpty() ? null : new RejectionReason(rejectionReason),
                Registration.Status.valueOf(state)
        );
    }

}
