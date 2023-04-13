package com.durys.jakub.recruitmentapp.registration.application;

import com.durys.jakub.recruitmentapp.ddd.annotations.ApplicationService;
import com.durys.jakub.recruitmentapp.registration.domain.*;
import com.durys.jakub.recruitmentapp.registration.domain.exceptions.RegistrationExceptionType;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@ApplicationService
@RequiredArgsConstructor
public class RegistrationApplicationService {

    private final RegistrationRepository registrationRepository;


    public void register(OfferId offerId, String firstName, String lastName, String email,
                         String phoneNumber, byte[] cv) {

        Registration registration = new Registration(new RegistrationId(UUID.randomUUID()), offerId,
                new ApplicantInformation(firstName, lastName, email, phoneNumber), new Cv(cv));

        registrationRepository.save(registration);
    }

    public void reject(RegistrationId registrationId, String reason) {

        Registration registration = registrationRepository.load(registrationId);

        registration.markAsRejected(reason);

        registrationRepository.save(registration);
    }

    public void accept(RegistrationId registrationId) {
        Registration registration = registrationRepository.load(registrationId);

        registration.markAsAccepted();

        registrationRepository.save(registration);
    }


}
