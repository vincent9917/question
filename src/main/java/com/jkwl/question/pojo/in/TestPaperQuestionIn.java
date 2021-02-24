package com.jkwl.question.pojo.in;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class TestPaperQuestionIn {
    @NotNull(message = "试卷id为空", groups = {Delete.class})
    private Long testPaperId;
    @NotNull(message = "题目id为空", groups = {Create.class, Review.class})
    private Long questionId;
    @PositiveOrZero
    @DecimalMin(value = "0.5", groups = {Create.class})
    private Double score;
    @NotEmpty(message = "提交答案为空", groups = {Review.class})
    private List<String> submitAnswer = new ArrayList<>();

    public interface Create{}
    public interface Delete{}
    public interface Review{}

    public Long getTestPaperId() {
        return testPaperId;
    }

    public void setTestPaperId(Long testPaperId) {
        this.testPaperId = testPaperId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<String> getSubmitAnswer() {
        return submitAnswer;
    }

    public void setSubmitAnswer(List<String> submitAnswer) {
        this.submitAnswer = submitAnswer;
    }
}
