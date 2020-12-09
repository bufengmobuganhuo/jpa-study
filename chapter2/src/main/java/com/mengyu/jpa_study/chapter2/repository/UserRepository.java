package com.mengyu.jpa_study.chapter2.repository;

import com.mengyu.jpa_study.chapter2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author yuzhang
 * @date 2020/12/8 下午6:41
 * TODO
 */
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User>, CustomizedUserRepository {

}
