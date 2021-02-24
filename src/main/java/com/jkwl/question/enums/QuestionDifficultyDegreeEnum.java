package com.jkwl.question.enums;

import com.jkwl.question.common.BusinessException;

/**
 * 题目的难度
 * degree表示题目难度，目前支持1-5
 */
public enum QuestionDifficultyDegreeEnum {

    VERY_EASY("VERY_EASY",1),
    EASY("EASY",2),
    MEDIOCRE("MEDIOCRE",3),
    HARD("HARD",4),
    VERY_HARD("VERY_HARD",5);

    private final String tag;
    private final Integer degree;

    QuestionDifficultyDegreeEnum(String tag, Integer degree) {
        this.tag = tag;
        this.degree = degree;
    }

    public String getTag() {
        return tag;
    }

    public Integer getDegree() {
        return degree;
    }

    public static QuestionDifficultyDegreeEnum ofDegree(Integer degree){
        for (QuestionDifficultyDegreeEnum degreeEnum:QuestionDifficultyDegreeEnum.values()){
            if (degreeEnum.getDegree().equals(degree))
                return degreeEnum;
        }
        throw new BusinessException("题目难度超出定义范围");
    }
}
