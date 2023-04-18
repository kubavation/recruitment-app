package com.durys.jakub.recruitmentapp.reviewer.domain;

import java.util.Collection;
import java.util.Optional;

public interface ReviewerRepository {
    Collection<Reviewer> load();
    Optional<Reviewer> load(ReviewerId reviewerId);
}
