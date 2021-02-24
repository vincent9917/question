package com.jkwl.question.enums;

public enum QuestionTypeEnum {
    //TODO,建议不使用中文,因为易出现如下问题:
    //1.极端情况下修改库时，因为SSH终端问题导致的编码错误引发修改错误
    //2.中文有GBK和UTF8两种常见编码方式，有部分人习惯用GBK而非UTF8
    //3.避免网络传输过程中因为前后端编码方式不一致导致的乱码
    //应该把具体如何显示和处理、存放逻辑当成是两件事，把最后呈现交给前端处理或者DTO处理
    /**
     * 单选题
     */
    SINGLE_CHOICE("SINGLE_CHOICE"),
    /**
     * 多选题
     */
    MULTIPLE_CHOICE("MULTIPLE_CHOICE"),
    /**
     * 选择题，用于解决可以先不添加选项的情况
     * UNKNOWN_CHOICE类型不能用于答题
     */
    UNKNOWN_CHOICE("UNKNOWN_CHOICE");

    /**
     * 题的类型
     */
    //TODO 这里用TYPE更为贴切
    private String type;

    QuestionTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    //TODO ENUM不应该设置Setter
}
