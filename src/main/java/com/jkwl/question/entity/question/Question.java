package com.jkwl.question.entity.question;

import com.jkwl.question.entity.IdEntity;
import com.jkwl.question.enums.OptionChoiceEnum;
import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;
import com.jkwl.question.enums.QuestionTypeEnum;
import org.hibernate.annotations.SortNatural;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "question")
@EntityListeners(AuditingEntityListener.class)
public class Question extends IdEntity {

    @Version
    private Long version;

    /**
     * 题干
     * HTML格式，存储题目的题干
     */
    @Column(name = "content", length = 2048, nullable = false)
    private String content;

    /**
     * 解析
     * HTML格式，存储题目的解析
     */
    @Column(name = "explanation", length = 4096, nullable = false)
    private String explanation;

    /**
     * 题的类型
     * 单选题和多选题，便于筛选
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private QuestionTypeEnum type = QuestionTypeEnum.UNKNOWN_CHOICE;

    /**
     * 题的难度
     * 题目的难度，see {@link com.jkwl.question.enums.QuestionDifficultyDegreeEnum}
     */
    @Convert(converter = QuestionDifficultyDegreeEnumConverter.class)
    @Column(name = "difficulty_degree", nullable = false)
    private QuestionDifficultyDegreeEnum difficultyDegree = QuestionDifficultyDegreeEnum.MEDIOCRE;

    @CreatedDate
    @Column(name = "create_time" ,updatable = false, nullable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "create_user_id", nullable = false)
    @CreatedBy
    private Long createUserId;

    @Column(name = "last_modify_user_id", nullable = false)
    @LastModifiedBy
    private Long lastModifyUserId;

    @Column(name = "create_by_department", nullable = false)
    private String createByDepartment;

    /**
     * 问题的选项
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @SortNatural
    private SortedSet<Option> options = new TreeSet<>();

    protected Question() {
    }

    public Question(@Nullable String content,
                    @Nullable String explanation,
                    @Nullable QuestionDifficultyDegreeEnum difficultyDegree) {
        this.content = content;
        this.explanation = explanation;
        this.difficultyDegree = difficultyDegree;
    }

    public boolean update(String content, String explanation, QuestionDifficultyDegreeEnum difficultyDegree) {
        this.content = content;
        this.explanation = explanation;
        this.difficultyDegree = difficultyDegree;
        return true;
    }

    public boolean containOption(String content) {
        for (Option option : this.options) {
            if (option.getContent().equals(content))
                return true;
        }
        return false;
    }

    public boolean addOption(String content, Boolean isCorrect) {
        if (containOption(content))
            return false;
        Integer sort = this.options.size() + 1;
        Option option = new Option(content, sort, isCorrect);
        option.setQuestion(this);
        this.options.add(option);
        updateType();
        return true;
    }

    public boolean updateOption(Long optionId, String content, Boolean isCorrect) {
        for (Option opt : this.options) {
            if (optionId.equals(opt.getId())) {
                opt.setContent(content);
                opt.setCorrect(isCorrect);
                updateType();
                return true;
            }
        }
        return false;
    }

    public boolean deleteOption(Long optionId) {
        if (this.options.size() == 1) return false;
        for (Option option : this.options) {
            if (option.getId().equals(optionId)) {
                if (!this.options.remove(option))
                    return false;
                option.detach();
                updateType();
                return true;
            }
        }
        return false;
    }

    public boolean isCorrect(List<OptionChoiceEnum> optionChoiceEnums) {
        if (optionChoiceEnums.size() == 0)
            return false;
        Set<Integer> optionChoiceIndexes = optionChoiceEnums.stream().map(OptionChoiceEnum::getIndex).collect(Collectors.toSet());
        Set<Integer> correctOptionSorts = this.options.stream().filter(Option::getCorrect).map(Option::getSort).collect(Collectors.toSet());
        for (Integer sort : correctOptionSorts) {
            for (Integer optionChoiceIndex : optionChoiceIndexes) {
                if (!sort.equals(optionChoiceIndex)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isChoicesCorrect(List<String> optionChoices) {
        List<OptionChoiceEnum> optionChoiceEnums = new ArrayList<>();
        for (String optionChoice : optionChoices) {
            optionChoiceEnums.add(OptionChoiceEnum.ofChoice(optionChoice));
        }
        return isCorrect(optionChoiceEnums);
    }

    private void updateType() {
        if (getAnswerCount() == 1)
            type = QuestionTypeEnum.SINGLE_CHOICE;
        else if (getAnswerCount() > 1)
            type = QuestionTypeEnum.MULTIPLE_CHOICE;
        else
            type = QuestionTypeEnum.UNKNOWN_CHOICE;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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

    public QuestionTypeEnum getType() {
        return type;
    }

    public void setType(QuestionTypeEnum type) {
        this.type = type;
    }

    public QuestionDifficultyDegreeEnum getDifficultyDegree() {
        return difficultyDegree;
    }

    public void setDifficultyDegree(QuestionDifficultyDegreeEnum difficultyDegree) {
        this.difficultyDegree = difficultyDegree;
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

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(Long lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    public SortedSet<Option> getOptions() {
        return options;
    }

    public void setOptions(SortedSet<Option> options) {
        this.options = options;
    }

    public String getCreateByDepartment() {
        return createByDepartment;
    }

    public void setCreateByDepartment(String createByDepartment) {
        this.createByDepartment = createByDepartment;
    }

    public Integer getAnswerCount() {
        return this.options.stream().filter(Option::getCorrect).collect(Collectors.toSet()).size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id.equals(question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Question{" +
                "version=" + version +
                ", id=" + id +
                ", content='" + content + '\'' +
                ", explanation='" + explanation + '\'' +
                ", type=" + type +
                ", difficultyDegree=" + difficultyDegree +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUserId=" + createUserId +
                ", lastModifyUserId=" + lastModifyUserId +
                ", createByDepartment='" + createByDepartment + '\'' +
                ", options=" + options +
                '}';
    }

    @PrePersist
    public void prePersist() {
        System.out.println("question-实体持久化之前:" + this.toString());
        this.createByDepartment = this.createUserId + ":部门信息";
    }

    @PostPersist
    public void postPersist() {
        System.out.println("question-在数据库中存储新实体:" + this.toString());
    }

    @PostLoad
    public void postLoad() {
        System.out.println("question-从数据库中检索实体后:" + this.toString());
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("question-当一个实体被识别为被修改时:" + this.toString());
    }

    @PostUpdate
    public void postUpdate() {
        System.out.println("question-更新数据库中的实体:" + this.toString());
    }

    @PreRemove
    public void preRemove() {
        System.out.println("question-在EntityManager中标记要删除的实体:" + this.toString());
    }

    @PostRemove
    public void postRemove() {
        System.out.println("question-从数据库中删除实体:" + this.toString());
    }
}
