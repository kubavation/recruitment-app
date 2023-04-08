package com.durys.jakub.recruitmentapp.ddd.annotations.events;

public class DomainEventRegistry {


    public static DomainEventRegistry instance() {
        return new DomainEventRegistry();
    }

    public void publish(DomainEvent event) {
        //todo emit
    }

}
