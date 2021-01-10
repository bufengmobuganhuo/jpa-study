package com.mengyu.chapter3.repository;

import com.mengyu.chapter3.entity.Address;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yuzhang
 * @date 2021/1/10 下午1:58
 * TODO
 */
public interface AddressRepository extends JpaRepository<Address,Long> {
    @Override
    // 使用@EntityGraph查询所有Address时，指定使用名称为"getAllUserInfo"
    // 采用默认的EntityGraph.EntityGraphType.FETCH；
    // 即只有在name = "getAllUserInfo"的实体图配置的userInfo属性上采用Eager模式，
    // 其他关联关系属性没有指定，默认采用LAZY模式；
    @EntityGraph(value = "getAllUserInfo")
    List<Address> findAll();
}
