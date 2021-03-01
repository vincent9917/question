package com.jkwl.question.facade.Impl;

import com.jkwl.question.facade.TestPaperFacade;
import com.jkwl.question.pojo.in.TestPaperIn;
import com.jkwl.question.pojo.out.QuestionOut;
import com.jkwl.question.pojo.out.TestPaperQuestionOut;
import com.jkwl.question.pojo.out.TestPaperOut;
import com.jkwl.question.service.QuestionService;
import com.jkwl.question.service.TestPaperService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class TestPaperFacadeImpl implements TestPaperFacade {

    private final QuestionService questionService;
    private final TestPaperService testPaperService;

    public TestPaperFacadeImpl(QuestionService questionService, TestPaperService testPaperService) {
        this.questionService = questionService;
        this.testPaperService = testPaperService;
    }

    @Override
    public TestPaperOut findById(Long id) {
        TestPaperOut testPaperOut = testPaperService.find(id);
        Set<Long> allQuestionId = testPaperOut.getAllQuestionId();
        List<QuestionOut> questionOuts = questionService.findByIdIn(allQuestionId);
        for (TestPaperQuestionOut testPaperQuestionOut : testPaperOut.getQuestions()) {
            testPaperQuestionOut.setQuestion(questionOuts.stream().filter((questionOut -> questionOut.getId().equals(testPaperQuestionOut.getQuestionId()))).findFirst().orElse(null));
        }
        return testPaperOut;
    }

    @Override
    public TestPaperOut create(TestPaperIn testPaperParam) {
        return testPaperService.create(testPaperParam);
    }

    @Override
    public TestPaperOut reviewTestPaper(TestPaperIn testPaperParam) {
        TestPaperOut testPaperOut = testPaperService.reviewTestPaper(testPaperParam);
        Set<Long> allQuestionId = testPaperOut.getAllQuestionId();
        List<QuestionOut> questionOuts = questionService.findByIdIn(allQuestionId);
        for (TestPaperQuestionOut testPaperQuestionOut : testPaperOut.getQuestions()) {
            testPaperQuestionOut.setQuestion(questionOuts.stream().filter((questionOut -> questionOut.getId().equals(testPaperQuestionOut.getQuestionId()))).findFirst().orElse(null));
        }
        return testPaperOut;
    }
}
