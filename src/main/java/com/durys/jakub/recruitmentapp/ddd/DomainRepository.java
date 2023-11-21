package com.durys.jakub.recruitmentapp.ddd;

public interface DomainRepository<T extends AggregateRoot> {
    void save(T aggregate);
}
