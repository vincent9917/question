package com.jkwl.question.pojo.in;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class TestPaperIn {
    @NotNull(message = "试卷id为空", groups = {Update.class, Delete.class, Review.class})
    private Long id;
    @NotNull(message = "试卷名称为空", groups = {Create.class, Update.class})
    private String name;
    @Valid
    @NotEmpty(message = "请提交至少一个题目", groups = {Create.class, Review.class})
    private List<TestPaperQuestionIn> questions = new ArrayList<>();

    public interface Create{}
    public interface Update{}
    public interface Delete{}
    public interface Review{}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestPaperQuestionIn> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TestPaperQuestionIn> questions) {
        this.questions = questions;
    }
}
