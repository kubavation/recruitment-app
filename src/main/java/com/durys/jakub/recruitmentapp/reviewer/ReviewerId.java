package com.durys.jakub.recruitmentapp.reviewer;

import java.io.Serializable;
import java.util.UUID;

public record ReviewerId(UUID value) implements Serializable {
}
