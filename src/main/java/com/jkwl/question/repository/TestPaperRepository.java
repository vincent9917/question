package com.jkwl.question.repository;

import com.jkwl.question.entity.testpaper.TestPaper;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface TestPaperRepository extends BaseRepository<TestPaper, Long> {
    @Override
    @EntityGraph(attributePaths = {"questions"})
    Optional<TestPaper> findById(Long id);
}
