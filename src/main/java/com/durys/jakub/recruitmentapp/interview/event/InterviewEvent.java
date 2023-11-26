package com.durys.jakub.recruitmentapp.interview.event;

import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public sealed interface InterviewEvent extends DomainEvent {

    record InterviewCreated(UUID id, Instant at) implements InterviewEvent {
    }
}
