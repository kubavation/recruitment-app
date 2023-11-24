package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;

import java.time.LocalDateTime;

record Review(ReviewerId reviewerId, String opinion, LocalDateTime createdAt) {

}
