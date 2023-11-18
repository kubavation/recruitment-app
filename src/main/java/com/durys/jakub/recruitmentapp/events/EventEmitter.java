package com.durys.jakub.recruitmentapp.events;

public interface EventEmitter {
    void emit(DomainEvent event);
}
