package com.jkwl.question.controller;

import com.jkwl.question.common.Result;
import com.jkwl.question.pojo.in.*;
import com.jkwl.question.pojo.out.QuestionAnswerOut;
import com.jkwl.question.pojo.out.QuestionOut;
import com.jkwl.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping()
    public Result<QuestionOut> find(@RequestParam(value = "id") Long id) {
        return Result.success(questionService.find(id));
    }

    @PostMapping(value = "/create")
    public Result<QuestionOut> create(@RequestBody @Validated({QuestionIn.Create.class, OptionIn.Create.class}) QuestionIn questionParam) {
        return Result.success(questionService.create(questionParam));
    }

    @PostMapping(value = "/modify")
    public Result<QuestionOut> modify(@RequestBody @Validated({QuestionIn.Update.class}) QuestionIn questionParam) {
        questionService.update(questionParam.getId(), questionParam.getContent(), questionParam.getExplanation(), questionParam.getDifficulty());
        return Result.success(null);
    }

    @DeleteMapping(value = "/delete")
    public Result<QuestionOut> delete(@RequestParam(value = "id") Long id) {
        return Result.success(questionService.delete(id));
    }

    @PostMapping(value = "/addOption")
    public Result<QuestionOut> addOption(@RequestBody @Validated({OptionIn.Create.class}) OptionIn optionParam) {
        return Result.success(questionService.addOption(optionParam.getQuestionId(), optionParam.getContent(), optionParam.getCorrect()));
    }

    @PostMapping(value = "/modifyOption")
    public Result<QuestionOut> modifyOption(@RequestBody @Validated({OptionIn.Update.class}) OptionIn optionParam) {
        return Result.success(questionService.updateOption(optionParam.getQuestionId(), optionParam.getId(), optionParam.getContent(), optionParam.getCorrect()));
    }

    @DeleteMapping(value = "/removeOption")
    public Result<QuestionOut> removeOption(@RequestBody @Validated({OptionIn.Delete.class}) OptionIn optionParam) {
        return Result.success(questionService.deleteOption(optionParam.getQuestionId(), optionParam.getId()));
    }

    @PostMapping(value = "/answerQuestions")
    public Result<List<QuestionAnswerOut>> answerQuestions(@RequestBody List<QuestionAnswerIn> answers) {
        Long userId = 1L;
        return Result.success(questionService.answerQuestions(userId, answers));
    }
}
