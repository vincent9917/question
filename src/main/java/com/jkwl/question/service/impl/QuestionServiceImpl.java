package com.jkwl.question.service.impl;

import com.jkwl.question.common.BusinessException;
import com.jkwl.question.config.assembler.AssemblerFactory;
import com.jkwl.question.entity.question.Question;
import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;
import com.jkwl.question.pojo.in.*;
import com.jkwl.question.pojo.out.QuestionAnswerOut;
import com.jkwl.question.pojo.out.QuestionOut;
import com.jkwl.question.repository.QuestionRepository;
import com.jkwl.question.service.QuestionService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final AssemblerFactory assemblerFactory;

    public QuestionServiceImpl(QuestionRepository questionRepository,
                               AssemblerFactory assemblerFactory) {
        this.questionRepository = questionRepository;
        this.assemblerFactory = assemblerFactory;
    }

    @Cacheable(value = "question", key = "#id")
    @Override
    public QuestionOut find(Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (questionOptional.isEmpty()) {
            throw new BusinessException("题目不存在");
        }
        return assemblerFactory.assemble(questionOptional.get(), QuestionOut.class);
    }

    @Override
    public List<QuestionOut> findByIdIn(Collection<Long> ids) {
        List<Question> questions = questionRepository.findByIdIn(ids);
        return assemblerFactory.assemble(questions, QuestionOut.class);
    }

    @Transactional
    @Override
    public QuestionOut create(QuestionIn questionParam) {
        Question question = new Question(questionParam.getContent(), questionParam.getExplanation(), QuestionDifficultyDegreeEnum.ofDegree(questionParam.getDifficulty()));
        for (OptionIn optionParam : questionParam.getOptions()) {
            question.addOption(optionParam.getContent(), optionParam.getCorrect());
        }
        questionRepository.saveAndFlush(question);
        return assemblerFactory.assemble(question, QuestionOut.class);
    }

    @Transactional
    @CacheEvict(value = "question", key = "#id")
    @Override
    public QuestionOut update(Long id, String content, String explanation, Integer difficultyDegree) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (questionOptional.isEmpty()) {
            throw new BusinessException("题目不存在");
        }
        Question question = questionOptional.get();
        question.update(content, explanation, QuestionDifficultyDegreeEnum.ofDegree(difficultyDegree));
        return assemblerFactory.assemble(question, QuestionOut.class);
    }

    @CacheEvict(value = "question", key = "#id")
    @Transactional
    @Override
    public QuestionOut delete(Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (questionOptional.isEmpty()) {
            throw new BusinessException("题目不存在，删除失败");
        }
        Question question = questionOptional.get();
        questionRepository.delete(question);//TODO 这里调用deleteById是一样的，因为删除函数都是通过主键去找Hibernate Cache里的对象
        return assemblerFactory.assemble(question, QuestionOut.class);
    }

    @CacheEvict(value = "question", key = "#questionId")
    @Transactional
    @Override
    public QuestionOut addOption(Long questionId, String content, Boolean isCorrect) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()) {
            throw new BusinessException("题目不存在，选项添加失败");
        }
        Question question = questionOptional.get();
        if (!question.addOption(content, isCorrect)) {
            throw new BusinessException("选项内容重复，选项添加失败");
        }
        return assemblerFactory.assemble(question, QuestionOut.class);
    }

    @CacheEvict(value = "question", key = "#questionId")
    @Transactional
    @Override
    public QuestionOut updateOption(Long questionId, Long optionId, String content, Boolean isCorrect) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()) {
            throw new BusinessException("题目不存在，更新失败");
        }
        Question question = questionOptional.get();
        if (question.updateOption(optionId, content, isCorrect))
            throw new BusinessException("选项不存在，更新失败");
        return assemblerFactory.assemble(question, QuestionOut.class);
    }

    @CacheEvict(value = "question", key = "#questionId")
    @Transactional
    @Override
    public QuestionOut deleteOption(Long questionId, Long optionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()) {
            throw new BusinessException("题目不存在，删除失败");
        }
        Question question = questionOptional.get();
        if (!question.deleteOption(optionId)) {
            throw new BusinessException("选项不存在，删除失败");
        }
        return assemblerFactory.assemble(question, QuestionOut.class);
    }

    @Transactional
    @Override
    public List<QuestionAnswerOut> answerQuestions(Long userId, List<QuestionAnswerIn> submitAnswers) {
        Set<Long> questionIds = submitAnswers.stream().map(QuestionAnswerIn::getQuestionId).collect(Collectors.toSet());
        if (questionIds.size() != submitAnswers.size())
            throw new BusinessException("回答的题目存在重复");

        List<Question> questions = questionRepository.findByIdIn(questionIds);
        if (questions.isEmpty() || questions.size() != submitAnswers.size()) {
            throw new BusinessException("题目数和回答数不匹配");
        }
        List<QuestionAnswerOut> resultOuts = new ArrayList<>(submitAnswers.size());
        for (QuestionAnswerIn submitAnswer : submitAnswers) {
            for (Question question : questions) {
                if (!submitAnswer.getQuestionId().equals(question.getId())) {
                    continue;
                }
                QuestionAnswerOut questionAnswerOut = new QuestionAnswerOut();
                questionAnswerOut.setCorrect(question.isChoicesCorrect(submitAnswer.getAnswers()));
                questionAnswerOut.setQuestionId(question.getId());
                questionAnswerOut.setMyAnswer(submitAnswer.getAnswers());
                resultOuts.add(questionAnswerOut);
            }
        }
        return resultOuts;
    }
}
