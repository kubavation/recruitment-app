package com.durys.jakub.recruitmentapp.cv.domain.events;

import com.durys.jakub.recruitmentapp.cv.domain.CvId;
import com.durys.jakub.recruitmentapp.ddd.annotations.events.DomainEvent;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationId;

public record CvDeclined(CvId cvId, RegistrationId registrationId) implements DomainEvent {
}
