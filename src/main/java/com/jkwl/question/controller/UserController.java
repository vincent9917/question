package com.jkwl.question.controller;

import com.jkwl.question.common.Result;
import com.jkwl.question.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping(value = "/find")
    public Result<?> find() {
        return Result.success(userService.find());
    }

    @GetMapping(value = "/findJoin")
    public Result<?> findJoin() {
        return Result.success(userService.findJoin());
    }
}
