package com.jkwl.question.service;

import com.jkwl.question.pojo.out.ChoiceOut;

public interface ChoiceService {
    ChoiceOut find(Long id);
    ChoiceOut create();
    void answer();
}
