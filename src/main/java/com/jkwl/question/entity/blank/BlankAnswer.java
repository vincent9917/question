package com.jkwl.question.entity.blank;

import com.jkwl.question.entity.IdEntity;

import javax.persistence.*;

@Entity
public class BlankAnswer extends IdEntity {

    @Column(name = "content", length = 64, nullable = false)
    private String content;

    @Column(name = "sort", nullable = false)
    private Integer sort;

    @ManyToOne
    @JoinColumn(name = "blank_id", referencedColumnName = "id", nullable = false, updatable = false,
            foreignKey = @ForeignKey(name = "null"))
    private Blank blank;

    protected BlankAnswer() {}

    public String getContent() {
        return content;
    }

    public Integer getSort() {
        return sort;
    }

    public Blank getBlank() {
        return blank;
    }
}
