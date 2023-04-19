package com.durys.jakub.recruitmentapp.technicalinterview.infrastructure;

import com.durys.jakub.recruitmentapp.technicalinterview.domain.TechnicalInterviewRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class TechnicalInterviewConfiguration {

    @Bean
    public TechnicalInterviewRepository technicalInterviewRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcTechnicalInterviewRepository(jdbcTemplate);
    }

}
