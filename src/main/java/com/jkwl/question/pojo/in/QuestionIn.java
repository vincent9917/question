package com.jkwl.question.pojo.in;

import com.jkwl.question.pojo.QuestionBase;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class QuestionIn {
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;
    @NotNull(message = "题干不能为空")
    private String content;
    @NotNull(message = "解析不能为空")
    private String explanation;
    @Max(value = 5, message = "难度最高为5")
    @Min(value = 1, message = "难度最小为1")
    private Integer difficulty;
    @Valid
    @NotNull(message = "选项不能为空", groups = Create.class)
    @Size(min = 1, max = 4, message = "选项至少一个，至多四个", groups = {Create.class})
    private List<OptionIn> options;

    public interface Create{}
    public interface Update{}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public List<OptionIn> getOptions() {
        return options;
    }

    public void setOptions(List<OptionIn> options) {
        this.options = options;
    }
}
