package com.durys.jakub.recruitmentapp.waitingroom.domain.events;

import com.durys.jakub.recruitmentapp.ddd.annotations.events.DomainEvent;
import com.durys.jakub.recruitmentapp.waitingroom.domain.ApplicantInformation;
import com.durys.jakub.recruitmentapp.waitingroom.domain.RegistrationId;

public record RegistrationDeclined(RegistrationId registrationId, ApplicantInformation applicantInformation,
                                   String reason) implements DomainEvent {
}
