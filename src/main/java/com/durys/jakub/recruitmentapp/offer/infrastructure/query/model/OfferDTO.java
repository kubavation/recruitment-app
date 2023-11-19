package com.durys.jakub.recruitmentapp.offer.infrastructure.query.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class OfferDTO {
    private UUID id;
    private String position;
    private String description;
    private Integer limit;
    private LocalDate from;
    private LocalDate to;
    private String state;
    private LocalDateTime closedAt;

    public OfferDTO(UUID id, String position, String description, Integer limit,
                    LocalDate from, LocalDate to, String state, LocalDateTime closedAt) {
        this.id = id;
        this.position = position;
        this.description = description;
        this.limit = limit;
        this.from = from;
        this.to = to;
        this.state = state;
        this.closedAt = closedAt;
    }
}
