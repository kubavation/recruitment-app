package com.durys.jakub.recruitmentapp.registration.domain.events;

import com.durys.jakub.recruitmentapp.ddd.annotations.events.DomainEvent;
import com.durys.jakub.recruitmentapp.registration.domain.ApplicantInformation;
import com.durys.jakub.recruitmentapp.registration.domain.OfferId;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationId;

public record RegistrationDeclined(RegistrationId registrationId, OfferId offerId,
                                   ApplicantInformation applicantInformation, String reason) implements DomainEvent {
}
