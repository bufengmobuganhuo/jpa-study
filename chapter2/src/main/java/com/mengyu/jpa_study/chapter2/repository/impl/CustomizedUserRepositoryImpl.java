package com.mengyu.jpa_study.chapter2.repository.impl;

import com.mengyu.jpa_study.chapter2.entity.User;
import com.mengyu.jpa_study.chapter2.repository.CustomizedUserRepository;

import javax.persistence.EntityManager;

/**
 * @author yuzhang
 * @date 2020/12/8 下午8:09
 * TODO
 */
public class CustomizedUserRepositoryImpl<T> implements CustomizedUserRepository<T>{

    // 通过构造函数注入
    private final EntityManager entityManager;

    public CustomizedUserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void actualUpdateName(T user) {
        if (user instanceof User){
            entityManager.merge(user);
            entityManager.flush();
        }
    }
}
