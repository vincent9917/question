package com.jkwl.question.entity.problem;

import com.jkwl.question.entity.IdEntity;
import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;
import com.jkwl.question.enums.QuestionTypeEnum;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@DynamicInsert
@DynamicUpdate
@Entity
public class Choice extends AbstractProblem implements AbstractProblemAbility {

    @OneToMany(mappedBy = "choice", cascade = CascadeType.ALL, orphanRemoval = true)
    @SortNatural
    private SortedSet<ChoiceOption> options = new TreeSet<>();

    protected Choice() {}

    public Choice(QuestionTypeEnum type, String content, String explanation, QuestionDifficultyDegreeEnum difficultyDegree) {
        super(type, content, explanation, difficultyDegree);
    }

    public SortedSet<ChoiceOption> getOptions() {
        return options;
    }

    protected boolean containOption(String content) {
        for (ChoiceOption option : this.options) {
            if (option.getContent().equals(content))
                return true;
        }
        return false;
    }

    //添加选项
    public boolean addChoiceOption(String content, Boolean isCorrect) {
        if (containOption(content))
            return false;
        Integer sort = this.options.size() + 1;
        ChoiceOption option = new ChoiceOption(content, sort, isCorrect);
        option.setChoice(this);
        this.options.add(option);
        return true;
    }

    public boolean deleteOption(Long optionId) {
        if (this.options.size() == 1) return false;
        for (ChoiceOption option : this.options) {
            if (option.getId().equals(optionId)) {
                if (!this.options.remove(option))
                    return false;
                option.detach();
                return true;
            }
        }
        return false;
    }

    @Override
    public void answer(AbstractProblemAbility abstractProblem) {
        System.out.println("选择题答题");
    }
}
