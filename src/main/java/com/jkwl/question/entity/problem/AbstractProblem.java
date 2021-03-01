package com.jkwl.question.entity.problem;

import com.jkwl.question.entity.IdEntity;
import com.jkwl.question.entity.question.QuestionDifficultyDegreeEnumConverter;
import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;
import com.jkwl.question.enums.QuestionTypeEnum;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AbstractProblem extends IdEntity {
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private QuestionTypeEnum type = QuestionTypeEnum.UNKNOWN_CHOICE;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "explanation", nullable = false)
    private String explanation;

    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @Convert(converter = QuestionDifficultyDegreeEnumConverter.class)
    @Column(name = "difficulty_degree", nullable = false)
    private QuestionDifficultyDegreeEnum difficultyDegree = QuestionDifficultyDegreeEnum.MEDIOCRE;

    protected AbstractProblem() {
    }

    public AbstractProblem(QuestionTypeEnum type, String content, String explanation, QuestionDifficultyDegreeEnum difficultyDegree) {
        this.type = type;
        this.content = content;
        this.explanation = explanation;
        this.difficultyDegree = difficultyDegree;
    }

    public QuestionTypeEnum getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public QuestionDifficultyDegreeEnum getDifficultyDegree() {
        return difficultyDegree;
    }

    public String getExplanation() {
        return explanation;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
