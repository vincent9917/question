package com.jkwl.question.service;

import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;
import com.jkwl.question.enums.QuestionTypeEnum;
import com.jkwl.question.pojo.out.QuestionOut;

import java.util.List;

public interface QuestionService {
    QuestionOut find(Long id);
    List<QuestionOut> findAll();
    QuestionOut create(QuestionTypeEnum type, String content, String explanation, QuestionDifficultyDegreeEnum difficultyDegree, Long targetId);
}
