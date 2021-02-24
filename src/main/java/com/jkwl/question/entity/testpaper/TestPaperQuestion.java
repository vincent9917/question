package com.jkwl.question.entity.testpaper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "test_paper_question", indexes = {@Index(name = "question_and_test_paper_index", columnList = "question_id,test_paper_id", unique = true)})
@IdClass(value = TestPaperQuestionKey.class)
public class TestPaperQuestion implements Comparable<TestPaperQuestion> {

    @Column(name = "score", nullable = false)
    private Double score;

    @Id
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Id
    @Column(name = "test_paper_id", nullable = false)
    private Long testPaperId;

    @Column(name = "sort", nullable = false)
    private Integer sort;

    @ManyToOne
    @JoinColumn(name = "test_paper_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false,
            foreignKey = @ForeignKey(name = "null"))
    private TestPaper testPaper;

    public TestPaperQuestion() {
    }

    public TestPaperQuestion(@NotNull Double score, @NotNull Long questionId, @NotNull Integer sort) {
        this.score = score;
        this.questionId = questionId;
        this.sort = sort;
    }

    protected void detach() {
        this.testPaper = null;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public TestPaper getTestPaper() {
        return testPaper;
    }

    public void setTestPaper(TestPaper testPaper) {
        this.testPaper = testPaper;
    }

    @Override
    public int compareTo(TestPaperQuestion o) {
        return this.getSort() - o.getSort();
    }

    @PrePersist
    public void prePersist() {
        this.testPaperId = this.testPaper.getId();
    }
}
