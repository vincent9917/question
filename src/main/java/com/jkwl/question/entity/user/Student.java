package com.jkwl.question.entity.user;

import com.jkwl.question.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "student")
@Table(name = "t_user")
public class Student extends IdEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "student_no")
    private String studentNo;

    public Student(String name, String studentNo) {
        this.name = name;
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
}
