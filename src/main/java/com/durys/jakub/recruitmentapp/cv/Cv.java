package com.durys.jakub.recruitmentapp.cv;

import java.util.UUID;

public class Cv {

    private final CvId id;
    private final String fileName;
    private final byte[] content;

    public Cv(CvId id, String fileName, byte[] content) {
        this.id = id;
        this.fileName = fileName;
        this.content = content;
    }

    public Cv(String fileName, byte[] content) {
        this.id = new CvId(UUID.randomUUID());
        this.fileName = fileName;
        this.content = content;
    }
}
