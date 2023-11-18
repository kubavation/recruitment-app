package com.durys.jakub.recruitmentapp.ddd;

import com.durys.jakub.recruitmentapp.events.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {

    private final List<DomainEvent> domainEvents;

    protected AggregateRoot(List<DomainEvent> domainEvents) {
        this.domainEvents = domainEvents;
    }

    protected AggregateRoot() {
        this.domainEvents = new ArrayList<>();
    }

    public void add(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> domainEvents() {
        return new ArrayList<>(domainEvents);
    }
}
