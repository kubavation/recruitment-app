package com.durys.jakub.recruitmentapp.technicalinterview.domain;

import java.util.Optional;

public interface TechnicalInterviewRepository {
    Optional<TechnicalInterview> load(TechnicalInterviewId id);
    void save(TechnicalInterview interview);
}
