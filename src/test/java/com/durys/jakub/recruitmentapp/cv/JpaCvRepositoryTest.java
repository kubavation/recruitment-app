package com.durys.jakub.recruitmentapp.cv;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaCvRepositoryTest {

    @Autowired
    private CvRepository cvRepository;

    @Autowired
    private EntityManager entityManager;


    @BeforeEach
    @Transactional
    void delete() {
        List<Cv> cvs = entityManager
                .createQuery("SELECT cv FROM Cv cv", Cv.class).getResultList();
        cvs.stream()
                .forEach(cv -> entityManager.remove(cv));
    }

    @Test
    @Transactional
    void shouldSaveCv() {

        Cv cv = new Cv("cv.pdf", new byte[] {});
        CvId id = cvRepository.save(cv);

        assertNotNull(id);
    }

}