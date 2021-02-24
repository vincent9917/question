package com.jkwl.question;

import com.jkwl.question.facade.TestPaperFacade;
import com.jkwl.question.pojo.in.TestPaperIn;
import com.jkwl.question.pojo.in.TestPaperQuestionIn;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@DisplayName("测试用例")
@SpringBootTest
public class MyFirstTestCaseTest {

    @Autowired
    private TestPaperFacade testPaperFacade;

    public static Stream<TestPaperIn> createParamProvider() {
        TestPaperIn testPaperIn = new TestPaperIn();
        testPaperIn.setName("我的第一张试卷");
        TestPaperQuestionIn question = new TestPaperQuestionIn();
        question.setQuestionId(1L);
        question.setScore(0.6);
        TestPaperQuestionIn question1 = new TestPaperQuestionIn();
        question1.setQuestionId(2L);
        question1.setScore(0.6);
        testPaperIn.getQuestions().add(question);
        testPaperIn.getQuestions().add(question1);
        return Stream.of(testPaperIn);
    }

    public static Stream<TestPaperIn> reviewParamProvider() {
        TestPaperIn testPaperIn = new TestPaperIn();
        testPaperIn.setId(1L);
        TestPaperQuestionIn question = new TestPaperQuestionIn();
        question.setQuestionId(1L);
        question.getSubmitAnswer().add("A");
        question.getSubmitAnswer().add("B");
        TestPaperQuestionIn question1 = new TestPaperQuestionIn();
        question1.setQuestionId(2L);
        question1.getSubmitAnswer().add("B");
        testPaperIn.getQuestions().add(question);
        testPaperIn.getQuestions().add(question1);
        return Stream.of(testPaperIn);
    }

    @DisplayName("测试创建试卷")
    @ParameterizedTest(name = "第{index}次测试")
    @MethodSource("createParamProvider")
    void testCreatePaper(TestPaperIn testPaperIn) {
        testPaperFacade.create(testPaperIn);
    }

    @DisplayName("测试查询试卷")
    @ParameterizedTest(name = "第{index}次测试")
    @ValueSource(longs = {1, 2})
    void testFindPaper(Long id) {
        testPaperFacade.findById(id);
    }

    @DisplayName("测试批阅试卷")
    @ParameterizedTest(name = "第{index}次测试")
    @MethodSource("reviewParamProvider")
    void testReviewPaper(TestPaperIn testPaperIn) {
        testPaperFacade.reviewTestPaper(testPaperIn);
    }
}
