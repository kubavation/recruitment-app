package com.durys.jakub.recruitmentapp.interview.domain;

import com.durys.jakub.recruitmentapp.ddd.DomainRepository;

public interface InterviewRepository extends DomainRepository<Interview> {
    Interview load(Interview.Id id);
}
