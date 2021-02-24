package com.jkwl.question.pojo.out;

import com.jkwl.question.pojo.QuestionBase;

import java.util.ArrayList;
import java.util.List;

//TODO 特定场景使用
//TODO 不需要的属性用@JsonIgnoreProperties去掉,例如C端可能不需要难度值，原则上不返回不需要的数据，避免暴露信息
public class QuestionOut extends QuestionBase {

    protected List<OptionOut> options = new ArrayList<>();

    public void addOption(OptionOut optionOut) {
        this.options.add(optionOut);
    }

    public List<OptionOut> getOptions() {
        return options;
    }

    public void setOptions(List<OptionOut> options) {
        this.options = options;
    }
}
