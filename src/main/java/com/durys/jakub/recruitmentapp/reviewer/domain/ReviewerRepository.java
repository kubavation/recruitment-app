package com.durys.jakub.recruitmentapp.reviewer.domain;

import java.util.List;

public interface ReviewerRepository {
    Reviewer load(ReviewerId id);
    void save(Reviewer reviewer);
    void update(List<Reviewer> reviewers);
}
