package com.durys.jakub.recruitmentapp.cv.infrastructure;

import com.durys.jakub.recruitmentapp.cv.domain.CvRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class CvConfiguration {

    @Bean
    public CvRepository cvRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcCvRepository(jdbcTemplate);
    }
}
