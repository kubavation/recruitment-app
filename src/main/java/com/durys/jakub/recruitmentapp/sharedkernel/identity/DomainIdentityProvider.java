package com.durys.jakub.recruitmentapp.sharedkernel.identity;

public interface DomainIdentityProvider<T> {
    T identity();
}
