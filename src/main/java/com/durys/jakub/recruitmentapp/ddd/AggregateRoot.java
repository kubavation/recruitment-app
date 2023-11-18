package com.durys.jakub.recruitmentapp.ddd;

import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.util.HashSet;
import java.util.Set;

public abstract class AggregateRoot {

    private final Set<DomainEvent> domainEvents;

    protected AggregateRoot(Set<DomainEvent> domainEvents) {
        this.domainEvents = domainEvents;
    }

    protected AggregateRoot() {
        this.domainEvents = new HashSet<>();
    }

    public void add(DomainEvent event) {
        domainEvents.add(event);
    }

    public Set<DomainEvent> domainEvents() {
        return new HashSet<>(domainEvents);
    }
}
