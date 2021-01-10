package com.mengyu.jpa_study.chapter2.repository.impl;

import com.mengyu.jpa_study.chapter2.entity.User;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * @author yuzhang
 * @date 2020/12/8 下午8:42
 * TODO
 */
@Transactional(readOnly = true)
public class CustomerBaseRepository<T,ID> extends SimpleJpaRepository<T,ID> {
    private final JpaEntityInformation<T,?> entityInformation;
    private final EntityManager em;
    public CustomerBaseRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    public CustomerBaseRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityInformation = null;
        this.em = em;
    }

    /**
     * 覆盖SimpleJpaRepository对save方法的默认实现
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> S save(S entity) {
        em.persist(entity);
        return entity;
    }
}
