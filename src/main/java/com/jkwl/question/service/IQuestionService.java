package com.jkwl.question.service;

import com.jkwl.question.pojo.in.QuestionAnswerIn;
import com.jkwl.question.pojo.in.QuestionIn;
import com.jkwl.question.pojo.out.QuestionAnswerOut;
import com.jkwl.question.pojo.out.QuestionOut;

import java.util.Collection;
import java.util.List;
import java.util.Set;

//TODO Entity只允许生存在Service内，所以只能返回DTO
public interface IQuestionService {

    //TODO 可能存在空值的用Optional
    // Service返回如果可能存在空值返回Optional,如果是Collection返回EmptyList、EmptySet
    // 这里的逻辑不存在空值
    QuestionOut find(Long id);

    List<QuestionOut> findByIdIn(Collection<Long> ids);

    QuestionOut create(QuestionIn questionParam);

    QuestionOut update(Long id, String content, String explanation, Integer difficultyDegree);

    QuestionOut delete(Long id);

    QuestionOut addOption(Long questionId,String content,Boolean isCorrect);

    QuestionOut updateOption(Long questionId,Long optionId,String content,Boolean isCorrect);

    QuestionOut deleteOption(Long questionId, Long optionId);

    List<QuestionAnswerOut> answerQuestions(Long userId, List<QuestionAnswerIn> submitAnswers);
}
