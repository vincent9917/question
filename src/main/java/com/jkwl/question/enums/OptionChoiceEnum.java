package com.jkwl.question.enums;

/**
 * 答案选项
 * 仅支持ABCD四种，映射数字1-4.
 */
public enum OptionChoiceEnum {
    A("A",1),
    B("B",2),
    C("C",3),
    D("D",4);

    private final String choice;
    private final Integer index;

    OptionChoiceEnum(String choice, Integer index) {
        this.choice = choice;
        this.index = index;
    }

    public String getChoice() {
        return choice;
    }

    public Integer getIndex() {
        return index;
    }

    public static OptionChoiceEnum ofChoice(String choice){
        for (OptionChoiceEnum choiceEnum:OptionChoiceEnum.values()){
            if (choiceEnum.getChoice().equals(choice))
                return choiceEnum;
        }
        return null;
    }

    public static OptionChoiceEnum ofIndex(Integer index){
        for (OptionChoiceEnum choiceEnum:OptionChoiceEnum.values()){
            if (choiceEnum.getIndex().equals(index))
                return choiceEnum;
        }
        return null;
    }
}
