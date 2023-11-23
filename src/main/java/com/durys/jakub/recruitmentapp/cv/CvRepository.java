package com.durys.jakub.recruitmentapp.cv;

public interface CvRepository{
    Cv load(CvId id);
    CvId save(Cv cv);
}
