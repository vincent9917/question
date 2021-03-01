package com.jkwl.question.entity.question;

import com.jkwl.question.entity.AbstractProblem;
import com.jkwl.question.entity.QuestionAbility;
import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;
import com.jkwl.question.enums.QuestionTypeEnum;
import javax.persistence.*;

@Entity
public class Question extends AbstractProblem {

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    protected Question() {}

    public void answer(QuestionAbility questionAbility) {
        questionAbility.answer();
    }

    public Question(QuestionTypeEnum type, String content, String explanation, QuestionDifficultyDegreeEnum difficultyDegree, Long targetId) {
        super(type, content, explanation, difficultyDegree);
        this.targetId = targetId;
    }

    public Long getTargetId() {
        return targetId;
    }

}
