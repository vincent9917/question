package com.jkwl.question.facade.Impl;

import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;
import com.jkwl.question.enums.QuestionTypeEnum;
import com.jkwl.question.facade.ProblemFacade;
import com.jkwl.question.pojo.out.ChoiceOut;
import com.jkwl.question.service.ChoiceService;
import com.jkwl.question.service.ProblemService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ProblemFacadeImpl implements ProblemFacade {

    private final ChoiceService choiceService;
    private final ProblemService problemService;

    public ProblemFacadeImpl(ChoiceService choiceService, ProblemService problemService) {
        this.choiceService = choiceService;
        this.problemService = problemService;
    }

    @Transactional
    @Override
    public ChoiceOut createChoice() {
        ChoiceOut choiceOut = choiceService.create();
        problemService.create(QuestionTypeEnum.ofType(choiceOut.getType()), choiceOut.getContent(), choiceOut.getExplanation(), QuestionDifficultyDegreeEnum.ofDegree(choiceOut.getDifficultyDegree()), choiceOut.getId());
        return choiceOut;
    }
}
