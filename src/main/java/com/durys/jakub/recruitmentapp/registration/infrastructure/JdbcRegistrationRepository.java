package com.durys.jakub.recruitmentapp.registration.infrastructure;

import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationId;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class JdbcRegistrationRepository implements RegistrationRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public Registration load(RegistrationId id) {
        return null;
    }

    @Override
    public void save(Registration registration) {

    }
}
