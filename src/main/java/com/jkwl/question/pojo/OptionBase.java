package com.jkwl.question.pojo;

//TODO BASE按照Entity最基本的属性建立，不带关系
public class OptionBase {

    protected Long id;

    protected String content;

    protected Integer sort;

    protected Boolean correct;

    protected Long chooseCount;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Long getChooseCount() {
        return chooseCount;
    }

    public void setChooseCount(Long chooseCount) {
        this.chooseCount = chooseCount;
    }
}
