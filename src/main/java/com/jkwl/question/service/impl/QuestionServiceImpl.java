package com.jkwl.question.service.impl;

import com.jkwl.question.common.BusinessException;
import com.jkwl.question.config.assembler.AssemblerFactory;
import com.jkwl.question.entity.question.Question;
import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;
import com.jkwl.question.enums.QuestionTypeEnum;
import com.jkwl.question.pojo.out.QuestionOut;
import com.jkwl.question.repository.QuestionRepository;
import com.jkwl.question.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AssemblerFactory assemblerFactory;

    public QuestionServiceImpl(QuestionRepository questionRepository,
                               AssemblerFactory assemblerFactory) {
        this.questionRepository = questionRepository;
        this.assemblerFactory = assemblerFactory;
    }

    @Override
    public QuestionOut find(Long id) {
        Optional<Question> problemOptional = questionRepository.findById(id);
        if (problemOptional.isEmpty()) {
            throw new BusinessException("题目未找到！");
        }
        Question question = problemOptional.get();
        return assemblerFactory.assemble(question, QuestionOut.class);
    }

    @Override
    public List<QuestionOut> findAll() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            return new ArrayList<>();
        }
        return assemblerFactory.assemble(questions, QuestionOut.class);
    }

    @Override
    public QuestionOut create(QuestionTypeEnum type, String content, String explanation, QuestionDifficultyDegreeEnum difficultyDegree, Long targetId) {
        Question question = new Question(type, content, explanation, difficultyDegree, targetId);
        questionRepository.saveAndFlush(question);
        return assemblerFactory.assemble(question, QuestionOut.class);
    }
}
