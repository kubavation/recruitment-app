package com.durys.jakub.recruitmentapp.technicalinterview.domain;

import reactor.core.publisher.Mono;

public interface TechnicalInterviewRepository {
    Mono<TechnicalInterview> load(TechnicalInterviewId id);
    Mono<TechnicalInterview> save(TechnicalInterview interview);
}
