package com.durys.jakub.recruitmentapp.cv;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public record CvId(UUID value) implements Serializable {}

