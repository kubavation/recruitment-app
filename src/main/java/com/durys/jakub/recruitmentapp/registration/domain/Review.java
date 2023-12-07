package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;

import java.time.LocalDateTime;

record Review(ReviewerId reviewerId, String opinion, LocalDateTime createdAt) {

}
