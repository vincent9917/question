package com.jkwl.question.entity.user;

import com.jkwl.question.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "teacher")
@Table(name = "t_user")
public class Teacher extends IdEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "teacher_no")
    private String teacherNo;

    public Teacher(String name, String teacherNo) {
        this.name = name;
        this.teacherNo = teacherNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }
}
