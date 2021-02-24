package com.jkwl.question.assembler;

import com.jkwl.question.config.assembler.Assembler;
import com.jkwl.question.entity.question.Option;
import com.jkwl.question.entity.question.Question;
import com.jkwl.question.enums.OptionChoiceEnum;
import com.jkwl.question.pojo.out.OptionOut;
import com.jkwl.question.pojo.out.QuestionOut;
import org.springframework.stereotype.Component;

@Component
public class QuestionAssembler implements Assembler<Question, QuestionOut> {

    @Override
    public QuestionOut assemble(Question question) {
        QuestionOut questionOut = new QuestionOut();
        questionOut.setId(question.getId());
        questionOut.setContent(question.getContent());
        questionOut.setExplanation(question.getExplanation());
        questionOut.setType(question.getType().getType());
        questionOut.setDifficultyDegree(question.getDifficultyDegree().getDegree());

        for (Option option : question.getOptions()) {
            OptionOut optionOut = new OptionOut();
            optionOut.setId(option.getId());
            optionOut.setContent(option.getContent());
            optionOut.setCorrect(option.getCorrect());
            optionOut.setSort(option.getSort());
            optionOut.setAlphaSort(OptionChoiceEnum.ofIndex(option.getSort()).getChoice());
            optionOut.setChooseCount(option.getChooseCount());
            questionOut.addOption(optionOut);
        }
        return questionOut;
    }
}
