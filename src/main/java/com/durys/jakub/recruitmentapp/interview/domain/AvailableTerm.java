package com.durys.jakub.recruitmentapp.interview.domain;

import java.time.LocalDate;
import java.time.LocalTime;

record AvailableTerm(LocalDate date, LocalTime from, LocalTime to) {
}
