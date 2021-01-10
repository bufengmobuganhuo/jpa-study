package com.mengyu.jpa_study.chapter2.repository;

import com.mengyu.jpa_study.chapter2.entity.User;

/**
 * @author yuzhang
 * @date 2020/12/8 下午8:08
 * TODO
 */
public interface CustomizedUserRepository<T> {
    void actualUpdateName(T user);
}
