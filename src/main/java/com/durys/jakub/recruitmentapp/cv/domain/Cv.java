package com.durys.jakub.recruitmentapp.cv.domain;

import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
public class Cv {

    private final CvId id;
    private Set<Opinion> opinions;

    public Cv(CvId id) {
        this.id = id;
        this.opinions = Collections.emptySet();
    }
}
