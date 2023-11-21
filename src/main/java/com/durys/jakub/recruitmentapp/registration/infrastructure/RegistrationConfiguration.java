package com.durys.jakub.recruitmentapp.registration.infrastructure;

import com.durys.jakub.recruitmentapp.registration.domain.RegistrationRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RegistrationConfiguration {

    @Bean
    RegistrationRepository registrationRepository(EntityManager entityManager) {
        return new JpaRegistrationRepository(entityManager);
    }
}
