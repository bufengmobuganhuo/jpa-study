package com.mengyu.jpa_study.chapter1.repository.user;

import com.mengyu.jpa_study.chapter1.entity.user.User;
import com.mengyu.jpa_study.chapter1.dto.UserOnlyNameAndEmail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author yuzhang
 * @date 2020/11/15 下午3:15
 * TODO
 */
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmailAndName(String email, String name);

    @Query("select u from User u")
    Stream<User> findAllByCustomQueryAndStream(Pageable pageable);

    @Query("select u from User u")
    Slice<User> findAllByCustomQueryAndSlice(Pageable pageable);

    /**
     * 只返回部分字段
     * @param name
     * @return
     */
    List<UserOnlyNameAndEmail> findByName(String name);

    @Query("from User where name=:name")
    List<User> findByQuery(@Param("name") String name);

    /**
     * 普通查询
     * @param emailAddress
     * @return
     */
    @Query("select u from User u where u.email = ?1")
    List<User> findByEmailAddress(String emailAddress);

    /**
     * 模糊搜索
     * @param name
     * @return
     */
    @Query("select u from User u where u.name like %?1")
    List<User> findByNameEndsWith(String name);

    /**
     * 使用原生SQL
     * @param email
     * @return
     */
    @Query(value = "select * from user where email = ?1",nativeQuery = true)
    List<User> findByEmail(String email);

    /**
     * 原生SQL不支持通过传入Sort对象来实现排序，只能在SQL里写
     * @param email
     * @param order
     * @return
     */
    @Query(value = "select * from user where email = ?1 order by ?2",nativeQuery = true)
    List<User> findByEmail(String email,String order);

    /**
     * 排序
     * @param name
     * @param sort
     * @return
     */
    @Query("select u from User u where u.name like %?1%")
    List<User> findByAndSort(String name, Sort sort);

    /**
     * 利用JPQL动态查询用户信息
     * @param name
     * @param email
     * @return
     */
    @Query("select u.name as name, u.email as email " +
            "from User u " +
            "where (:name is null or u.name=:name) and (:email is null or u.email=:email)")
    List<UserOnlyNameAndEmail> findByUser(@Param("name")String name,@Param("email")String email);
}
