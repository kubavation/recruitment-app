package com.durys.jakub.recruitmentapp.reviewer;


import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;

public class Reviewer {

    public enum State {
        ACTIVE, ARCHIVED
    }

    private final ReviewerId id;
    private final String name;
    private State state;


    public Reviewer(ReviewerId id, String name) {
        this.id = id;
        this.name = name;
        activate();
    }

    public Reviewer(ReviewerId id, String name, State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public void activate() {

        if (state == State.ACTIVE) {
            throw new InvalidStateForOperationException("Cannot activate reviewer");
        }

        this.state = State.ACTIVE;
    }

    public void archive() {

        if (state == State.ARCHIVED) {
            throw new InvalidStateForOperationException("Cannot archive reviewer");
        }

        this.state = State.ARCHIVED;
    }
}
