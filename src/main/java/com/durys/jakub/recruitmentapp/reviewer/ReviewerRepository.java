package com.durys.jakub.recruitmentapp.reviewer;

public interface ReviewerRepository {
    Reviewer load(ReviewerId id);
    void save(Reviewer reviewer);
}
