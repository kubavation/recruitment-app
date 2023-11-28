package com.durys.jakub.recruitmentapp.sharedkernel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AvailableTerm(LocalDate date, LocalTime from, LocalTime to) {

    public boolean inRange(LocalDateTime at) {
        if (at.toLocalDate().equals(date)) {
            return !at.toLocalTime().isBefore(from) && !at.toLocalTime().isAfter(to);
        }
        return false;
    }
}
