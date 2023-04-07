package com.durys.jakub.recruitmentapp.waitingroom.domain.events;

import com.durys.jakub.recruitmentapp.ddd.annotations.events.DomainEvent;
import com.durys.jakub.recruitmentapp.waitingroom.domain.OfferId;
import com.durys.jakub.recruitmentapp.waitingroom.domain.RegistrationId;

public record RegistrationAccepted(RegistrationId registrationId, OfferId offerId, byte[] cv) implements DomainEvent {
}
