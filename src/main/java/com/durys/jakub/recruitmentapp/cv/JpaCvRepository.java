package com.durys.jakub.recruitmentapp.cv;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class JpaCvRepository implements CvRepository {

    private final EntityManager entityManager;

    @Override
    public Cv load(CvId id) {
        return entityManager.find(Cv.class, id);
    }

    @Override
    public CvId save(Cv cv) {
        entityManager.persist(cv);
        return cv.getId();
    }
}
