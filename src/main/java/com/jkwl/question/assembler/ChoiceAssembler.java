package com.jkwl.question.assembler;

import com.jkwl.question.config.assembler.Assembler;
import com.jkwl.question.entity.choice.Choice;
import com.jkwl.question.entity.choice.ChoiceOption;
import com.jkwl.question.enums.OptionChoiceEnum;
import com.jkwl.question.pojo.out.ChoiceOptionOut;
import com.jkwl.question.pojo.out.ChoiceOut;
import org.springframework.stereotype.Component;

@Component
public class ChoiceAssembler implements Assembler<Choice, ChoiceOut> {


    @Override
    public ChoiceOut assemble(Choice choice) {
        ChoiceOut choiceOut = new ChoiceOut();
        choiceOut.setId(choice.getId());
        choiceOut.setContent(choice.getContent());
        choiceOut.setExplanation(choice.getExplanation());
        choiceOut.setType(choice.getType().getType());
        choiceOut.setDifficultyDegree(choice.getDifficultyDegree().getDegree());
        for (ChoiceOption option : choice.getOptions()) {
            ChoiceOptionOut choiceOptionOut = new ChoiceOptionOut();
            choiceOptionOut.setAlphaSort(OptionChoiceEnum.ofIndex(option.getSort()).getChoice());
            choiceOptionOut.setId(option.getId());
            choiceOptionOut.setContent(option.getContent());
            choiceOptionOut.setSort(option.getSort());
            choiceOptionOut.setCorrect(option.getCorrect());
            choiceOut.addOption(choiceOptionOut);
        }
        return choiceOut;
    }
}
