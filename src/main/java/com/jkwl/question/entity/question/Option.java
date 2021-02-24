package com.jkwl.question.entity.question;

import com.jkwl.question.entity.IdEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "`option`",
        indexes = {@Index(name = "question_and_sort_index", columnList = "question_id,sort", unique = true)})
public class Option extends IdEntity implements Comparable<Option> {

    @Column(name = "content", length = 64, nullable = false)
    private String content;

    @Column(name = "sort", nullable = false)
    private Integer sort;

    //TODO 注意命名
    @Column(name = "correct", nullable = false)
    private Boolean correct = false;

    @Column(name = "choose_count", nullable = false)
    private Long chooseCount = 0L;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false, updatable = false,
            foreignKey = @ForeignKey(name = "null"))
    private Question question;

    protected Option() {
    }

    protected Option(String content, Integer sort) {
        this.content = content;
        this.sort = sort;
    }

    protected Option(String content, Integer sort, Boolean isCorrect) {
        this.content = content;
        this.sort = sort;
        this.correct = isCorrect;
    }

    protected void detach() {
        this.question = null;
    }

    protected Long increaseChooseCount(Long increment) {
        this.chooseCount = this.chooseCount + increment;
        return chooseCount;
    }

    protected Long increaseChooseCount() {
        return increaseChooseCount(1L);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getChooseCount() {
        return chooseCount;
    }

    public Long setChooseCount(Long chooseCount) {
        this.chooseCount = chooseCount;
        return this.chooseCount;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public int compareTo(Option o) {
        return this.getSort() - o.getSort();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return id.equals(option.id);
    }
}
