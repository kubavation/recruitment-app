package com.durys.jakub.recruitmentapp.registration.application;

import com.durys.jakub.recruitmentapp.ddd.ApplicationService;
import com.durys.jakub.recruitmentapp.registration.domain.*;
import com.durys.jakub.recruitmentapp.registration.domain.command.ApproveRegistrationCommand;
import com.durys.jakub.recruitmentapp.registration.domain.command.RejectRegistrationCommand;
import com.durys.jakub.recruitmentapp.registration.domain.command.SubmitRegistrationCommand;
import lombok.RequiredArgsConstructor;


@ApplicationService
@RequiredArgsConstructor
public class RegistrationApplicationService {

    private final RegistrationRepository registrationRepository;



    void handle(SubmitRegistrationCommand command) {

        Registration registration = RegistrationFactory.create(
                command.offerId(), command.firstName(), command.lastName(),
                command.email(), command.phoneNumber(), command.fileName(), command.file());

        registrationRepository.save(registration);
    }

    void handle(RejectRegistrationCommand command) {

        Registration registration = registrationRepository.load(new Registration.Id(command.registrationId()));

        registration.reject(command.reason());

        registrationRepository.save(registration);
    }

    void handle(ApproveRegistrationCommand command) {

        Registration registration = registrationRepository.load(new Registration.Id(command.registrationId()));

        registration.approve();

        registrationRepository.save(registration);

    }
}
