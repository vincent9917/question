package com.jkwl.question.pojo.in;

import java.util.List;

public class QuestionAnswerIn {
    private static final long serialVersionUID = 1L;
    private Long questionId;
    private List<String> answers;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
