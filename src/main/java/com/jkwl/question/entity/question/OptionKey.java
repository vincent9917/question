package com.jkwl.question.entity.question;

import java.io.Serializable;
import java.util.Objects;

public class OptionKey implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long questionId;
    private Integer sort;

    public OptionKey() {
    }

    public OptionKey(Long questionId, Integer sort) {
        this.questionId = questionId;
        this.sort = sort;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionKey optionKey = (OptionKey) o;
        return Objects.equals(questionId, optionKey.questionId) &&
                Objects.equals(sort, optionKey.sort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, sort);
    }
}
