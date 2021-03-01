package com.jkwl.question.facade.Impl;

import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;
import com.jkwl.question.enums.QuestionTypeEnum;
import com.jkwl.question.facade.QuestionFacade;
import com.jkwl.question.pojo.out.ChoiceOut;
import com.jkwl.question.service.ChoiceService;
import com.jkwl.question.service.QuestionService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class QuestionFacadeImpl implements QuestionFacade {

    private final ChoiceService choiceService;
    private final QuestionService questionService;

    public QuestionFacadeImpl(ChoiceService choiceService, QuestionService questionService) {
        this.choiceService = choiceService;
        this.questionService = questionService;
    }

    @Transactional
    @Override
    public ChoiceOut createChoice() {
        ChoiceOut choiceOut = choiceService.create();
        questionService.create(QuestionTypeEnum.ofType(choiceOut.getType()), choiceOut.getContent(), choiceOut.getExplanation(), QuestionDifficultyDegreeEnum.ofDegree(choiceOut.getDifficultyDegree()), choiceOut.getId());
        return choiceOut;
    }
}
