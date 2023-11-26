package com.durys.jakub.recruitmentapp.interview;

import java.util.UUID;

public class Interview {

    public record Id(UUID value) {
    }

    private final Id id;

}
