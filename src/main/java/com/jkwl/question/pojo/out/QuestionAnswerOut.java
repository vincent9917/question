package com.jkwl.question.pojo.out;

import java.util.List;

public class QuestionAnswerOut {
    protected Long questionId;
    protected List<String> myAnswer;
    protected Boolean correct;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<String> getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(List<String> myAnswer) {
        this.myAnswer = myAnswer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
}
