package com.jkwl.question.entity.problem;

import com.jkwl.question.entity.IdEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ChoiceOption extends IdEntity implements Comparable<ChoiceOption> {
    @Column(name = "content", length = 64, nullable = false)
    private String content;

    @Column(name = "sort", nullable = false)
    private Integer sort;

    @Column(name = "correct", nullable = false)
    private Boolean correct = false;

    @ManyToOne
    @JoinColumn(name = "choice_id", referencedColumnName = "id", nullable = false, updatable = false,
            foreignKey = @ForeignKey(name = "null"))
    private Choice choice;

    protected ChoiceOption() {}

    protected ChoiceOption(@NotNull String content, @NotNull Integer sort, @NotNull Boolean correct) {
        this.content = content;
        this.sort = sort;
        this.correct = correct;
    }

    protected void detach() {
        this.choice = null;
    }

    public String getContent() {
        return content;
    }

    public Integer getSort() {
        return sort;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    @Override
    public int compareTo(ChoiceOption o) {
        return this.getSort() - o.getSort();
    }
}
