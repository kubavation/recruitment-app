package com.durys.jakub.recruitmentapp.interview.infrastructure;

import com.durys.jakub.recruitmentapp.interview.domain.Interview;
import com.durys.jakub.recruitmentapp.interview.domain.InterviewRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryInterviewRepository implements InterviewRepository {

    private static Map<Interview.Id, Interview> DB = new ConcurrentHashMap<>();

    @Override
    public void save(Interview aggregate) {
        DB.put(aggregate.id(), aggregate);
    }

    @Override
    public Interview load(Interview.Id id) {
        return DB.get(id);
    }
}
