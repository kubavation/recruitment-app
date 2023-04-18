package com.durys.jakub.recruitmentapp.cv.infrastructure;

import com.durys.jakub.recruitmentapp.cv.domain.Cv;
import com.durys.jakub.recruitmentapp.cv.domain.CvId;
import com.durys.jakub.recruitmentapp.cv.domain.CvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class JdbcCvRepository implements CvRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Cv load(CvId id) {
        return null;
    }

    @Override
    public void save(Cv cv) {

    }
}
