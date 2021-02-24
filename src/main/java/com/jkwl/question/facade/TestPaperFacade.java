package com.jkwl.question.facade;

import com.jkwl.question.pojo.in.TestPaperIn;
import com.jkwl.question.pojo.out.TestPaperOut;

public interface TestPaperFacade {
    TestPaperOut findById(Long id);
    TestPaperOut create(TestPaperIn testPaperParam);
    TestPaperOut reviewTestPaper(TestPaperIn testPaperParam);
}
