package com.durys.jakub.recruitmentapp.waitingroom.domain;

public interface RegistrationRepository {

    Registration load(RegistrationId id);

    void save(Registration registration);

}
