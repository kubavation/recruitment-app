package com.durys.jakub.recruitmentapp.cv;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "CV")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Cv {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private final CvId id;

    @Column(name = "FILE_NAME")
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
