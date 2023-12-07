package com.durys.jakub.recruitmentapp.reviewer.domain;

public interface ReviewerRepository {
    Reviewer load(ReviewerId id);
    void save(Reviewer reviewer);
}
