package com.durys.jakub.recruitmentapp.reviewer.infastructure;

import com.durys.jakub.recruitmentapp.reviewer.domain.Reviewer;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryReviewerRepository implements ReviewerRepository {

    private static Map<ReviewerId, Reviewer> DB = new ConcurrentHashMap<>();

    @Override
    public Reviewer load(ReviewerId id) {
        return DB.get(id);
    }

    @Override
    public void save(Reviewer reviewer) {
        DB.put(reviewer.id(), reviewer);
    }
}
