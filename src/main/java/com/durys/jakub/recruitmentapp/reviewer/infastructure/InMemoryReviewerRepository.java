package com.durys.jakub.recruitmentapp.reviewer.infastructure;

import com.durys.jakub.recruitmentapp.reviewer.domain.Reviewer;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryReviewerRepository implements ReviewerRepository {

    private static final Map<ReviewerId, Reviewer> DB = new ConcurrentHashMap<>();

    @Override
    public Reviewer load(ReviewerId id) {
        return DB.get(id);
    }

    @Override
    public void save(Reviewer reviewer) {
        DB.put(reviewer.id(), reviewer);
    }

    @Override
    public void update(List<Reviewer> reviewers) {

        DB.values()
            .forEach(Reviewer::archive);

        reviewers.stream()
                .forEach(this::save);
    }
}
