package com.durys.jakub.recruitmentapp.technicalinterview.infrastructure;

import com.durys.jakub.recruitmentapp.technicalinterview.domain.TechnicalInterview;
import com.durys.jakub.recruitmentapp.technicalinterview.domain.TechnicalInterviewId;
import com.durys.jakub.recruitmentapp.technicalinterview.domain.TechnicalInterviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class JdbcTechnicalInterviewRepository implements TechnicalInterviewRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Mono<TechnicalInterview> load(TechnicalInterviewId id) {
        return null;
    }

    @Override
    public Mono<Void> save(TechnicalInterview interview) {
        return null;
    }
}
