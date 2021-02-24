package com.jkwl.question.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result defaultErrorHandler(Exception e){
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result handle(BusinessException e) {
        return Result.error(e.getMessage());
    }

}
