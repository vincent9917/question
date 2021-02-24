package com.jkwl.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, S> extends JpaRepository<T, S>, JpaSpecificationExecutor<T>, QuerydslPredicateExecutor<T> {
}
