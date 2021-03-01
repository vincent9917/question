package com.jkwl.question.service.impl;

import com.jkwl.question.common.BusinessException;
import com.jkwl.question.config.assembler.AssemblerFactory;
import com.jkwl.question.entity.question.Choice;
import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;
import com.jkwl.question.enums.QuestionTypeEnum;
import com.jkwl.question.pojo.out.ChoiceOut;
import com.jkwl.question.repository.ChoiceRepository;
import com.jkwl.question.service.ChoiceService;
import com.jkwl.question.service.ProblemDomainService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChoiceServiceImpl implements ChoiceService, ProblemDomainService {

    private final ChoiceRepository choiceRepository;
    private final AssemblerFactory assemblerFactory;

    public ChoiceServiceImpl(ChoiceRepository choiceRepository,
                             AssemblerFactory assemblerFactory) {
        this.choiceRepository = choiceRepository;
        this.assemblerFactory = assemblerFactory;
    }

    @Override
    public ChoiceOut find(Long id) {
        Optional<Choice> choiceOptional = choiceRepository.findById(id);
        if (choiceOptional.isEmpty()) {
            throw new BusinessException("选择题未找到");
        }
        Choice choice = choiceOptional.get();
        return assemblerFactory.assemble(choice, ChoiceOut.class);
    }

    @Override
    public ChoiceOut create() {
        Choice choice = new Choice(QuestionTypeEnum.SINGLE_CHOICE, "题干", "解析", QuestionDifficultyDegreeEnum.MEDIOCRE);
        choice.addChoiceOption("选项",true);
        choiceRepository.saveAndFlush(choice);
        return assemblerFactory.assemble(choice, ChoiceOut.class);
    }

    @Override
    public void answer() {

    }

    @Override
    public String getQuestionType() {
        return QuestionTypeEnum.SINGLE_CHOICE.getType();
    }
}
