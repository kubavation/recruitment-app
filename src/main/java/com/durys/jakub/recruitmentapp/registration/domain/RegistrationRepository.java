package com.durys.jakub.recruitmentapp.registration.domain;

public interface RegistrationRepository {

    Registration load(Registration.Id id);

    void save(Registration registration);

}
