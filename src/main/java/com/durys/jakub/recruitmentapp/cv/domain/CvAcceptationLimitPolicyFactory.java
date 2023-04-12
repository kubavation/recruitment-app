package com.durys.jakub.recruitmentapp.cv.domain;

class CvAcceptationLimitPolicyFactory {

    public static AcceptationLimit limit() {
        //todo get config
        return new AcceptationLimit(1, 1);
    }

}
