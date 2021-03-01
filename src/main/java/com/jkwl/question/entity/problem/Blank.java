package com.jkwl.question.entity.problem;

import com.jkwl.question.entity.IdEntity;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
public class Blank extends IdEntity implements ComplexChildAllow {

    @OneToMany(mappedBy = "blank", cascade = CascadeType.ALL, orphanRemoval = true)
    @SortNatural
    private SortedSet<BlankAnswer> blankAnswers = new TreeSet<>();

    protected Blank() {}

    public SortedSet<BlankAnswer> getBlankAnswers() {
        return blankAnswers;
    }
}
