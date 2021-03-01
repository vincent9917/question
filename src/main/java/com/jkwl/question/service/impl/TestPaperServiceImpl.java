package com.jkwl.question.service.impl;

import com.jkwl.question.common.BusinessException;
import com.jkwl.question.config.assembler.AssemblerFactory;
import com.jkwl.question.entity.testpaper.TestPaper;
import com.jkwl.question.entity.user.Student;
import com.jkwl.question.entity.user.Teacher;
import com.jkwl.question.pojo.in.QuestionAnswerIn;
import com.jkwl.question.pojo.in.TestPaperQuestionIn;
import com.jkwl.question.pojo.in.TestPaperIn;
import com.jkwl.question.pojo.out.QuestionAnswerOut;
import com.jkwl.question.pojo.out.TestPaperQuestionOut;
import com.jkwl.question.pojo.out.TestPaperOut;
import com.jkwl.question.repository.StudentRepository;
import com.jkwl.question.repository.TeacherRepository;
import com.jkwl.question.repository.TestPaperRepository;
import com.jkwl.question.service.QuestionService;
import com.jkwl.question.service.TestPaperService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestPaperServiceImpl implements TestPaperService {
    private final TestPaperRepository testPaperRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final AssemblerFactory assemblerFactory;
    private final QuestionService questionService;
    public TestPaperServiceImpl(TestPaperRepository testPaperRepository,
                                AssemblerFactory assemblerFactory,
                                QuestionService questionService,
                                TeacherRepository teacherRepository,
                                StudentRepository studentRepository) {
        this.testPaperRepository = testPaperRepository;
        this.assemblerFactory = assemblerFactory;
        this.questionService = questionService;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public TestPaperOut find(Long id) {

//        Teacher teacher = teacherRepository.findById(1L).get();
//        Student student = studentRepository.findById(2L).get();
        Teacher teacher1 = new Teacher("老师2", "222");
        Student student1 = new Student("学生2", "333");
        teacherRepository.saveAndFlush(teacher1);
        studentRepository.saveAndFlush(student1);
        System.out.println(1);

        Optional<TestPaper> testPaperOptional = testPaperRepository.findById(id);
        if (testPaperOptional.isEmpty()) {
            throw new BusinessException("试卷未找到");
        }
        TestPaper testPaper = testPaperOptional.get();
        return assemblerFactory.assemble(testPaper, TestPaperOut.class);
    }

    @Transactional
    @Override
    public TestPaperOut create(TestPaperIn testPaperParam) {
        TestPaper testPaper = new TestPaper(testPaperParam.getName());
        for (TestPaperQuestionIn question : testPaperParam.getQuestions()) {
            testPaper.addQuestion(question.getQuestionId(), question.getScore());
        }
        testPaperRepository.saveAndFlush(testPaper);
        return assemblerFactory.assemble(testPaper, TestPaperOut.class);
    }

    @Override
    public TestPaperOut reviewTestPaper(TestPaperIn testPaperIn) {
        Optional<TestPaper> testPaperOptional = testPaperRepository.findById(testPaperIn.getId());
        if (testPaperOptional.isEmpty()) {
            throw new BusinessException("试卷未找到");
        }
        TestPaper testPaper = testPaperOptional.get();
        TestPaperOut testPaperOut = assemblerFactory.assemble(testPaper, TestPaperOut.class);
        List<QuestionAnswerIn> submitAnswers = new ArrayList<>(testPaperIn.getQuestions().size());
        for (TestPaperQuestionIn testPaperQuestion : testPaperIn.getQuestions()) {
            QuestionAnswerIn questionAnswerIn = new QuestionAnswerIn();
            questionAnswerIn.setQuestionId(testPaperQuestion.getQuestionId());
            questionAnswerIn.setAnswers(testPaperQuestion.getSubmitAnswer());
            submitAnswers.add(questionAnswerIn);
        }
        List<QuestionAnswerOut> questionAnswerOuts = questionService.answerQuestions(1L, submitAnswers);
        for (TestPaperQuestionOut testPaperQuestion : testPaperOut.getQuestions()) {
            Optional<QuestionAnswerOut> questionAnswerOutOptional = questionAnswerOuts.stream().filter((questionAnswerOut -> questionAnswerOut.getQuestionId().equals(testPaperQuestion.getQuestionId()))).findFirst();
            if (questionAnswerOutOptional.isPresent()) {
                QuestionAnswerOut questionAnswerOut = questionAnswerOutOptional.get();
                testPaperQuestion.setMyAnswer(questionAnswerOut.getMyAnswer());
                testPaperQuestion.setCorrect(questionAnswerOut.getCorrect());
                testPaperQuestion.setMyScore(questionAnswerOut.getCorrect() ? testPaperQuestion.getScore() : 0f);
            }
        }
        return testPaperOut;
    }
}
