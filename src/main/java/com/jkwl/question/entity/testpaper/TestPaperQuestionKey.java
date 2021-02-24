package com.jkwl.question.entity.testpaper;

import java.io.Serializable;
import java.util.Objects;

public class TestPaperQuestionKey implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long questionId;
    private Long testPaperId;

    public TestPaperQuestionKey() {
    }

    public TestPaperQuestionKey(Long questionId, Long testPaperId) {
        this.questionId = questionId;
        this.testPaperId = testPaperId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getTestPaperId() {
        return testPaperId;
    }

    public void setTestPaperId(Long testPaperId) {
        this.testPaperId = testPaperId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPaperQuestionKey that = (TestPaperQuestionKey) o;
        return Objects.equals(questionId, that.questionId) &&
                Objects.equals(testPaperId, that.testPaperId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, testPaperId);
    }
}
