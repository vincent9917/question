package com.jkwl.question.assembler;

import com.jkwl.question.config.assembler.Assembler;
import com.jkwl.question.entity.question.Question;
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
        return questionOut;
    }
}
