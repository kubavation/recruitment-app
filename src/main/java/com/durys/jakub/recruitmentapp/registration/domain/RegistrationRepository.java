package com.durys.jakub.recruitmentapp.registration.domain;

public interface RegistrationRepository {

    Registration load(RegistrationId id);

    void save(Registration registration);

}
