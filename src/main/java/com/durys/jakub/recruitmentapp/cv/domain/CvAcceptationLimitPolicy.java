package com.durys.jakub.recruitmentapp.cv.domain;

public interface CvAcceptationLimitPolicy {
    AcceptationLimit calculateLimit(Integer opinionLimit);
}
