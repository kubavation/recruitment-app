package com.durys.jakub.recruitmentapp.reviewer.domain;


import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REVIEWER")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Reviewer {

    public enum State {
        ACTIVE, ARCHIVED
    }

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "ID"))
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
