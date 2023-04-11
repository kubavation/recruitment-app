package com.durys.jakub.recruitmentapp.cv.domain;

public interface CvRepository {
    Cv load(CvId id);
    void save(Cv cv);
}
