package com.durys.jakub.recruitmentapp.interview.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

record AvailableTerm(LocalDate date, LocalTime from, LocalTime to) {

    boolean inRange(LocalDateTime at) {
        if (at.toLocalDate().equals(date)) {
            return !at.toLocalTime().isBefore(from) && !at.toLocalTime().isAfter(to);
        }
        return false;
    }
}
