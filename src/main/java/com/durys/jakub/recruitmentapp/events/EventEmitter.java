package com.durys.jakub.recruitmentapp.events;

import com.durys.jakub.recruitmentapp.ddd.annotations.events.DomainEvent;

public interface EventEmitter {
    void emit(DomainEvent event);
}
