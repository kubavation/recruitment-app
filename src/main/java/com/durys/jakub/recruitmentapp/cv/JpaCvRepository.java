package com.durys.jakub.recruitmentapp.cv;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class JpaCvRepository implements CvRepository {

    private final EntityManager entityManager;

    @Override
    public Cv load(CvId id) {
        return null;
    }

    @Override
    public void save(Cv cv) {

    }
}
