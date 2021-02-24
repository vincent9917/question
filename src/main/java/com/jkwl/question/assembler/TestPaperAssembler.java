package com.jkwl.question.assembler;

import com.jkwl.question.config.assembler.Assembler;
import com.jkwl.question.entity.testpaper.TestPaper;
import com.jkwl.question.entity.testpaper.TestPaperQuestion;
import com.jkwl.question.pojo.out.TestPaperQuestionOut;
import com.jkwl.question.pojo.out.TestPaperOut;
import org.springframework.stereotype.Component;

@Component
public class TestPaperAssembler implements Assembler<TestPaper, TestPaperOut> {
    @Override
    public TestPaperOut assemble(TestPaper testPaper) {
        TestPaperOut testPaperOut = new TestPaperOut();
        testPaperOut.setId(testPaper.getId());
        testPaperOut.setName(testPaper.getName());
        testPaperOut.setCreateTime(testPaper.getCreateTime());
        testPaperOut.setUpdateTime(testPaper.getUpdateTime());
        for (TestPaperQuestion testPaperQuestion : testPaper.getQuestions()) {
            TestPaperQuestionOut testPaperQuestionOut = new TestPaperQuestionOut();
            testPaperQuestionOut.setQuestionId(testPaperQuestion.getQuestionId());
            testPaperQuestionOut.setScore(testPaperQuestion.getScore());
            testPaperQuestionOut.setSort(testPaperQuestion.getSort());
            testPaperOut.addElement(testPaperQuestionOut);
        }
        return testPaperOut;
    }
}
