package com.jkwl.question.pojo.out;

import com.jkwl.question.pojo.QuestionBase;

import java.util.ArrayList;
import java.util.List;

public class ChoiceOut extends QuestionBase {
    protected List<ChoiceOptionOut> options = new ArrayList<>();

    public void addOption(ChoiceOptionOut optionOut) {
        this.options.add(optionOut);
    }
}
