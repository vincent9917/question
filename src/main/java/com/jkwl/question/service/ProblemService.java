package com.jkwl.question.service;

import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;
import com.jkwl.question.enums.QuestionTypeEnum;
import com.jkwl.question.pojo.out.ProblemOut;

import java.util.List;

public interface ProblemService {
    ProblemOut find(Long id);
    List<ProblemOut> findAll();
    ProblemOut create(QuestionTypeEnum type, String content, String explanation, QuestionDifficultyDegreeEnum difficultyDegree, Long targetId);
}
