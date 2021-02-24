package com.jkwl.question.pojo.in;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

public class OptionIn {
    @NotNull(message = "选项id为空", groups = {Update.class, Delete.class})
    private Long id;
    @NotNull(message = "题目id为空", groups = {Create.class, Update.class, Delete.class})
    private Long questionId;
    @Length(max = 500)
    @NotNull(message = "选项内容为空", groups = {Create.class, Update.class})
    private String content;
    private Boolean correct = false;

    public interface Create{}
    public interface Update{}
    public interface Delete{}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
}
