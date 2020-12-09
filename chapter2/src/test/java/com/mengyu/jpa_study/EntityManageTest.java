package com.mengyu.jpa_study;

import com.mengyu.jpa_study.chapter2.entity.User;
import com.mengyu.jpa_study.chapter2.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/12/8 下午7:37
 * TODO
 */
@SpringBootTest
public class EntityManageTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(false)
    @Transactional
    void test_entityManager(){
        // 查找一个对象
        User user = entityManager.find(User.class,1L);
        Assertions.assertEquals(user.getAge(),15);
        // 改变user的属性
        user.setAge(20);
        //merge
        entityManager.merge(user);
        // 更新到数据库
        entityManager.flush();
        // 通过createQuery创建JPQL进行查询
        List<User> users = entityManager.createQuery("select u from User u where u.id=?1")
                .setParameter(1,1L)
                .getResultList();
        Assertions.assertEquals(users.get(0).getAge(),20);
    }
}
