package com.durys.jakub.recruitmentapp.invitation.domain;

import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;

import java.util.UUID;

public class Invitation extends AggregateRoot {

    record Id(UUID value) {}


}
