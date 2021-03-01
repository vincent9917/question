package com.jkwl.question.entity.blank;

import com.jkwl.question.entity.AbstractProblem;
import com.jkwl.question.entity.QuestionAbility;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
public class Blank extends AbstractProblem implements QuestionAbility {

    @OneToMany(mappedBy = "blank", cascade = CascadeType.ALL, orphanRemoval = true)
    @SortNatural
    private SortedSet<BlankAnswer> blankAnswers = new TreeSet<>();

    protected Blank() {}

    public SortedSet<BlankAnswer> getBlankAnswers() {
        return blankAnswers;
    }

    @Override
    public void answer() {

    }
}
