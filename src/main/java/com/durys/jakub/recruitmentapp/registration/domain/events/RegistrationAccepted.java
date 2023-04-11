package com.durys.jakub.recruitmentapp.registration.domain.events;

import com.durys.jakub.recruitmentapp.ddd.annotations.events.DomainEvent;
import com.durys.jakub.recruitmentapp.registration.domain.Cv;
import com.durys.jakub.recruitmentapp.registration.domain.OfferId;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationId;

public record RegistrationAccepted(RegistrationId registrationId, OfferId offerId, Cv cv) implements DomainEvent {
}
