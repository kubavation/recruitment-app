package com.durys.jakub.recruitmentapp.registration.application;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import com.durys.jakub.recruitmentapp.commons.identity.IdentityProvider;
import com.durys.jakub.recruitmentapp.cv.Cv;
import com.durys.jakub.recruitmentapp.cv.CvId;
import com.durys.jakub.recruitmentapp.cv.CvRepository;
import com.durys.jakub.recruitmentapp.ddd.ApplicationService;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.domain.OfferRepository;
import com.durys.jakub.recruitmentapp.registration.domain.*;
import static com.durys.jakub.recruitmentapp.registration.domain.command.RegistrationCommand.*;
import com.durys.jakub.recruitmentapp.reviewer.ReviewerId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@ApplicationService
@RequiredArgsConstructor
public class RegistrationApplicationService {

    private final RegistrationRepository registrationRepository;
    private final CvRepository cvRepository;
    private final OfferRepository offerRepository;
    private final IdentityProvider identityProvider;

    @Transactional
    public void handle(SubmitRegistrationCommand command) {

        Offer offer = offerRepository.load(new Offer.Id(command.offerId()));

        if (offer.isClosed()) {
            throw new InvalidStateForOperationException("Cannot register application to this offer");
        }

        CvId cvId = cvRepository.save(new Cv(command.fileName(), command.file()));

        Registration registration = RegistrationFactory.create(
                command.offerId(), command.firstName(), command.lastName(),
                command.email(), command.phoneNumber(), cvId);

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

    @Transactional
    public void handle(AddRegistrationOpinionCommand command) {

        Registration registration = registrationRepository.load(new Registration.Id(command.registrationId()));

        ReviewerId reviewerId = new ReviewerId(identityProvider.identifier());

        registration.addReview(reviewerId, command.opinion());

        registrationRepository.save(registration);

    }
}
