package com.durys.jakub.recruitmentapp.registration.infrastructure;

import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class JpaRegistrationRepository implements RegistrationRepository {

    private final EntityManager entityManager;

    @Override
    public Registration load(Registration.Id id) {
        return null;
    }

    @Override
    public void save(Registration registration) {

    }
}
