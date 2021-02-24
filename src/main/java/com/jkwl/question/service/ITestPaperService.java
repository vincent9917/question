package com.jkwl.question.service;

import com.jkwl.question.pojo.in.TestPaperIn;
import com.jkwl.question.pojo.out.TestPaperOut;

public interface ITestPaperService {
    TestPaperOut find(Long id);
    TestPaperOut create(TestPaperIn testPaperParam);
    TestPaperOut reviewTestPaper(TestPaperIn testPaperIn);
}
