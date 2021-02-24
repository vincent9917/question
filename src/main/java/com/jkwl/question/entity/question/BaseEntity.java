package com.jkwl.question.entity.question;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
    protected String id;

    protected BaseEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
