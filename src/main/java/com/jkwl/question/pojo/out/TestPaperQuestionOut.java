package com.jkwl.question.pojo.out;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jkwl.question.pojo.TestPaperQuestionBase;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"questionId"})
public class TestPaperQuestionOut extends TestPaperQuestionBase {
    protected Boolean correct;
    protected Double myScore;
    protected List<String> myAnswer;
    protected QuestionOut question;

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Double getMyScore() {
        return myScore;
    }

    public void setMyScore(Double myScore) {
        this.myScore = myScore;
    }

    public QuestionOut getQuestion() {
        return question;
    }

    public void setQuestion(QuestionOut question) {
        this.question = question;
    }

    public List<String> getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(List<String> myAnswer) {
        this.myAnswer = myAnswer;
    }
}
