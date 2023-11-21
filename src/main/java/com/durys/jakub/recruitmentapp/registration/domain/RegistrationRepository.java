package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.ddd.DomainRepository;

public interface RegistrationRepository extends DomainRepository<Registration> {

    Registration load(Registration.Id id);

    void save(Registration registration);

}
