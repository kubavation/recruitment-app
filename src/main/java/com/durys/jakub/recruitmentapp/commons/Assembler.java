package com.durys.jakub.recruitmentapp.commons;

import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;

public interface Assembler<T extends AggregateRoot, R> {
    T toAggregate(R object);
    R toModel(T aggregate);
}
