package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.cv.CvId;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationFactory {


    public static Registration create(UUID offerId, String applicantFirstName, String applicantLastName,
                                      String email, String phoneNumber, CvId cvId) {

        return new Registration(
                new Offer.Id(offerId),
                new ApplicantInformation(applicantFirstName, applicantLastName, email, phoneNumber),
                cvId
        );
    }

    public static Registration create(UUID id, UUID offerId, String applicantFirstName, String applicantLastName,
                                      String email, String phoneNumber, CvId cvId, String rejectionReason, String state) {

        return new Registration(
                new Registration.Id(id),
                new Offer.Id(offerId),
                new ApplicantInformation(applicantFirstName, applicantLastName, email, phoneNumber),
                cvId,
                rejectionReason == null ? null : new RejectionReason(rejectionReason),
                Registration.Status.valueOf(state)
        );
    }

}
