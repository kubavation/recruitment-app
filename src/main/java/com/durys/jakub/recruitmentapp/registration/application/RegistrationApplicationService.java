package com.durys.jakub.recruitmentapp.registration.application;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import com.durys.jakub.recruitmentapp.ddd.ApplicationService;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.domain.OfferRepository;
import com.durys.jakub.recruitmentapp.registration.domain.*;
import com.durys.jakub.recruitmentapp.registration.domain.command.ApproveRegistrationCommand;
import com.durys.jakub.recruitmentapp.registration.domain.command.RejectRegistrationCommand;
import com.durys.jakub.recruitmentapp.registration.domain.command.SubmitRegistrationCommand;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@ApplicationService
@RequiredArgsConstructor
public class RegistrationApplicationService {

    private final RegistrationRepository registrationRepository;
    private final OfferRepository offerRepository;

    @Transactional
    public void handle(SubmitRegistrationCommand command) {

        Offer offer = offerRepository.load(new Offer.Id(command.offerId()));

        if (offer.isClosed()) {
            throw new InvalidStateForOperationException("Cannot register application to this offer");
        }

        Registration registration = RegistrationFactory.create(
                command.offerId(), command.firstName(), command.lastName(),
                command.email(), command.phoneNumber(), command.fileName(), command.file());

        registrationRepository.save(registration);
    }

    @Transactional
    public void handle(RejectRegistrationCommand command) {

        Registration registration = registrationRepository.load(new Registration.Id(command.registrationId()));

        registration.reject(command.reason());

        registrationRepository.save(registration);
    }

    @Transactional
    public void handle(ApproveRegistrationCommand command) {

        Registration registration = registrationRepository.load(new Registration.Id(command.registrationId()));

        registration.approve();

        registrationRepository.save(registration);

    }
}
