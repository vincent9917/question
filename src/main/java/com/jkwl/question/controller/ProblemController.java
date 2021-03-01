package com.jkwl.question.controller;

import com.jkwl.question.common.Result;
import com.jkwl.question.facade.ProblemFacade;
import com.jkwl.question.pojo.out.ChoiceOut;
import com.jkwl.question.service.ProblemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/problem")
public class ProblemController {

    private final ProblemFacade problemFacade;

    public ProblemController(ProblemFacade problemFacade) {
        this.problemFacade = problemFacade;
    }

    @PostMapping(value = "/create")
    public Result<ChoiceOut> create() {
        return Result.success(problemFacade.createChoice());
    }
}
