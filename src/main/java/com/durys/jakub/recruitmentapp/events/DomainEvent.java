package com.durys.jakub.recruitmentapp.events;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
    Instant at();
    UUID id();
}
