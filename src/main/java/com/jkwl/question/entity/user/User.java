package com.jkwl.question.entity.user;

import com.jkwl.question.entity.IdEntity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends IdEntity {

    @Column(name = "name")
    private String name;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
