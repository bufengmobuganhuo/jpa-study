package com.mengyu.jpa_study.chapter1.repository.user;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * @author yuzhang
 * @date 2020/11/15 下午5:51
 * TODO
 */
@NoRepositoryBean
public interface MyBaseRepository<T, ID extends Serializable> extends Repository<T,ID> {
    T findOne(ID id);
    T save(T entity);
}
