package com.jkwl.question.pojo.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jkwl.question.pojo.TestPaperBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestPaperOut extends TestPaperBase {
    protected List<TestPaperQuestionOut> questions = new ArrayList<>();

    public void addElement(TestPaperQuestionOut testPaperQuestionOut) {
        this.questions.add(testPaperQuestionOut);
    }

    @JsonIgnore
    public Set<Long> getAllQuestionId() {
        return this.questions.stream().map(TestPaperQuestionOut::getQuestionId).collect(Collectors.toSet());
    }

    public List<TestPaperQuestionOut> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestPaperQuestionOut> questions) {
        this.questions = questions;
    }
}
