package com.durys.jakub.recruitmentapp.waitingroom.infrastructure;

import com.durys.jakub.recruitmentapp.waitingroom.domain.RegistrationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class RegistrationConfiguration {

    @Bean
    RegistrationRepository registrationRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegistrationRepository(jdbcTemplate);
    }
}
