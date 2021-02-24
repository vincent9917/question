package com.jkwl.question.controller;

import com.jkwl.question.common.Result;
import com.jkwl.question.facade.TestPaperFacade;
import com.jkwl.question.pojo.in.TestPaperIn;
import com.jkwl.question.pojo.in.TestPaperQuestionIn;
import com.jkwl.question.pojo.out.TestPaperOut;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/testPaper")
public class TestPaperController {
    private final TestPaperFacade testPaperFacade;

    public TestPaperController(TestPaperFacade testPaperFacade) {
        this.testPaperFacade = testPaperFacade;
    }

    @GetMapping(value = "/find")
    public Result<TestPaperOut> find(@RequestParam(value = "id") Long id) {
        return Result.success(testPaperFacade.findById(id));
    }

    @PostMapping(value = "/create")
    public Result<TestPaperOut> create(@RequestBody @Validated({TestPaperIn.Create.class, TestPaperQuestionIn.Create.class}) TestPaperIn testPaperParam) {
        return Result.success(testPaperFacade.create(testPaperParam));
    }

    @PostMapping(value = "/review")
    public Result<TestPaperOut> reviewTestPaper(@RequestBody @Validated({TestPaperIn.Review.class, TestPaperQuestionIn.Review.class}) TestPaperIn testPaperParam) {
        return Result.success(testPaperFacade.reviewTestPaper(testPaperParam));
    }
}
