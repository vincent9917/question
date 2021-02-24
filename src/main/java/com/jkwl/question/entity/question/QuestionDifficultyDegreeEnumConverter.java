package com.jkwl.question.entity.question;

import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;

import javax.persistence.AttributeConverter;

public class QuestionDifficultyDegreeEnumConverter implements AttributeConverter<QuestionDifficultyDegreeEnum,Integer> {
    @Override
    public Integer convertToDatabaseColumn(QuestionDifficultyDegreeEnum attribute) {
        return attribute.getDegree();
    }

    @Override
    public QuestionDifficultyDegreeEnum convertToEntityAttribute(Integer dbData) {
        return QuestionDifficultyDegreeEnum.ofDegree(dbData);
    }
}
