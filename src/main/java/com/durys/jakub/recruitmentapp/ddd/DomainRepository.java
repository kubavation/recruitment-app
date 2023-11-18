package com.durys.jakub.recruitmentapp.ddd;

public interface DomainRepository<T extends AggregateRoot> {
    T save(T aggregate);
}
