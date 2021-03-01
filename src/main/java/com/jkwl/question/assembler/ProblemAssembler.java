package com.jkwl.question.assembler;

import com.jkwl.question.config.assembler.Assembler;
import com.jkwl.question.entity.problem.Problem;
import com.jkwl.question.pojo.out.ProblemOut;
import org.springframework.stereotype.Component;

@Component
public class ProblemAssembler implements Assembler<Problem, ProblemOut> {

    @Override
    public ProblemOut assemble(Problem problem) {
        ProblemOut problemOut = new ProblemOut();
        problemOut.setId(problem.getId());
        problemOut.setContent(problem.getContent());
        problemOut.setExplanation(problem.getExplanation());
        problemOut.setType(problem.getType().getType());
        problemOut.setDifficultyDegree(problem.getDifficultyDegree().getDegree());
        return problemOut;
    }
}
