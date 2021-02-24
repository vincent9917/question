package com.jkwl.question.service.impl;

import com.jkwl.question.entity.user.QUser;
import com.jkwl.question.entity.user.User;
import com.jkwl.question.repository.UserRepository;
import com.jkwl.question.service.IUserService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * 动态查询
 */
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JPAQueryFactory queryFactory) {
        this.userRepository = userRepository;
        this.queryFactory = queryFactory;
    }

    @Override
    public Object find() {
        QUser qUser = QUser.user;
        List<User> fetch = queryFactory
                .selectFrom(qUser)//查询源
                .orderBy(qUser.id.desc())//根据id倒序
                .fetch();//执行查询并获取结果集

        User user = queryFactory.selectFrom(qUser).where(qUser.id.eq(1L)).fetchOne();
        Optional<User> one = userRepository.findOne(qUser.id.eq(1L));
        Iterable<User> a = userRepository.findAll(qUser.name.like("大"));
        System.out.println(1);
        return fetch;
    }

    @Override
    public Object findJoin() {
        return null;
//        QUser user = QUser.user;
//        QAddress address = QAddress.address;
//        return queryFactory.select(Projections.bean(
//                UserDto.class,//返回自定义实体的类型
//                user.id,
//                user.name,
//                address.name.as("addressName")
//        )).from(user, address).where(address.userId.eq(user.id).and(user.name.eq("大声道"))).orderBy(user.id.desc()).fetch();
    }
}
