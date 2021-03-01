package com.jkwl.question.service.impl;

import com.jkwl.question.common.BusinessException;
import com.jkwl.question.config.assembler.AssemblerFactory;
import com.jkwl.question.entity.problem.Problem;
import com.jkwl.question.enums.QuestionDifficultyDegreeEnum;
import com.jkwl.question.enums.QuestionTypeEnum;
import com.jkwl.question.pojo.out.ProblemOut;
import com.jkwl.question.repository.ProblemRepository;
import com.jkwl.question.service.ProblemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final AssemblerFactory assemblerFactory;

    public ProblemServiceImpl(ProblemRepository problemRepository,
                              AssemblerFactory assemblerFactory) {
        this.problemRepository = problemRepository;
        this.assemblerFactory = assemblerFactory;
    }

    @Override
    public ProblemOut find(Long id) {
        Optional<Problem> problemOptional = problemRepository.findById(id);
        if (problemOptional.isEmpty()) {
            throw new BusinessException("题目未找到！");
        }
        Problem problem = problemOptional.get();
        return assemblerFactory.assemble(problem, ProblemOut.class);
    }

    @Override
    public List<ProblemOut> findAll() {
        List<Problem> problems = problemRepository.findAll();
        if (problems.isEmpty()) {
            return new ArrayList<>();
        }
        return assemblerFactory.assemble(problems, ProblemOut.class);
    }

    @Override
    public ProblemOut create(QuestionTypeEnum type, String content, String explanation, QuestionDifficultyDegreeEnum difficultyDegree, Long targetId) {
        Problem problem = new Problem(type, content, explanation, difficultyDegree, targetId);
        problemRepository.saveAndFlush(problem);
        return assemblerFactory.assemble(problem, ProblemOut.class);
    }
}
