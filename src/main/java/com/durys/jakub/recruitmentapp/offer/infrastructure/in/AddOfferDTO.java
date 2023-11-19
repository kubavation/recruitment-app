package com.durys.jakub.recruitmentapp.offer.infrastructure.in;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AddOfferDTO {
    private String position;
    private String description;
    private Integer limit;
    private LocalDate from;
    private LocalDate to;
}
