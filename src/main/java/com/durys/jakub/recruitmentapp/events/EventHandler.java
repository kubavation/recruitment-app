package com.durys.jakub.recruitmentapp.events;

public interface EventHandler<T extends DomainEvent> {
    void handle(T t);
}
