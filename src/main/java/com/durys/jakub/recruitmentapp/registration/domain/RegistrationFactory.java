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

}
