package com.jkwl.question.entity.testpaper;

import com.jkwl.question.entity.IdEntity;
import org.hibernate.annotations.SortNatural;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "test_paper")
@EntityListeners(AuditingEntityListener.class)
public class TestPaper extends IdEntity {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "testPaper", cascade = CascadeType.ALL, orphanRemoval = true)
    @SortNatural
    private SortedSet<TestPaperQuestion> questions = new TreeSet<>();

    @CreatedDate
    @Column(name = "create_time" ,updatable = false, nullable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    protected TestPaper() {
    }

    public TestPaper(@NotNull String name) {
        this.name = name;
    }

    public boolean containElement(Long questionId) {
        for (TestPaperQuestion element : this.questions) {
            if (element.getQuestionId().equals(questionId))
                return true;
        }
        return false;
    }

    public boolean addQuestion(Long questionId, Double score) {
        if (containElement(questionId))
            return false;
        Integer sort = this.questions.size() + 1;
        TestPaperQuestion element = new TestPaperQuestion(score, questionId, sort);
        element.setTestPaper(this);
        this.questions.add(element);
        return true;
    }

    public boolean deleteElement(Long questionId) {
        if (this.questions.size() <= 1) return false;
        for (TestPaperQuestion question : this.questions) {
            if (question.getQuestionId().equals(questionId)) {
                if (!this.questions.remove(question))
                    return false;
                question.detach();
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SortedSet<TestPaperQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(SortedSet<TestPaperQuestion> questions) {
        this.questions = questions;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
