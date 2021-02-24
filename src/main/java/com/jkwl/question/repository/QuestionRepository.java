package com.jkwl.question.repository;

import com.jkwl.question.entity.question.Question;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @EntityGraph(attributePaths = {"options"})
    Optional<Question> findById(Long id);
    //TODO 同IQuestionService写的返回规则
    @EntityGraph(attributePaths = {"options"})
    List<Question> findByIdIn(Collection<Long> ids);
}
