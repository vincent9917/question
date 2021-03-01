package com.jkwl.question.pojo;

public class ProblemBase {
    private Long id;

    private String content;

    private String explanation;

    private String type;

    private Integer difficultyDegree;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDifficultyDegree() {
        return difficultyDegree;
    }

    public void setDifficultyDegree(Integer difficultyDegree) {
        this.difficultyDegree = difficultyDegree;
    }
}
