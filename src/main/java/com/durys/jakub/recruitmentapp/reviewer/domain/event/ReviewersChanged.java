package com.durys.jakub.recruitmentapp.reviewer.domain.event;

import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record ReviewersChanged(UUID id, Instant at) implements DomainEvent {}

