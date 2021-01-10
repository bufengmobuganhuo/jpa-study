package com.mengyu.chapter3.repository;


import com.mengyu.chapter3.entity.UserInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yuzhang
 * @date 2020/12/11 下午4:29
 * TODO
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    @Override
    // 指定EntityGraph引用的是在UserInfo实例里配置的name="addressGraph"的NamedEntityGraph
    // 此处采用LOAD类型，即被addressGraph配置的实体图属性address采用的fetch会变为FetchType.EAGER
    // 没有被addressGraph实体图配置关联关系的属性rooms还是之前配置的FetchType.EAGER模式
    @EntityGraph(value = "addressGraph", type = EntityGraph.EntityGraphType.LOAD)
    List<UserInfo> findAll();
}
