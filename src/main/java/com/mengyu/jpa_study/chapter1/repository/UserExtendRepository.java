package com.mengyu.jpa_study.chapter1.repository;

import com.mengyu.jpa_study.chapter1.dto.UserDto;
import com.mengyu.jpa_study.chapter1.dto.UserSimpleDto;
import com.mengyu.jpa_study.chapter1.entity.UserExtend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author yuzhang
 * @date 2020/11/24 下午7:29
 * TODO
 */
public interface UserExtendRepository extends JpaRepository<UserExtend, Long> {
    /**
     * 关联查询，并且对查出来的name做额外的处理
     *
     * @param id
     * @return
     */
    @Query("select new com.mengyu.jpa_study.chapter1.dto.UserDto(CONCAT(u.name,'JK123'),u.email, e.idCard) " +
            "from User u,UserExtend e " +
            "where u.id=e.userId and u.id=:id")
    List<UserDto> findByUserId(Long id);

    /**
     * 使用接口接收结果
     *
     * @param id
     * @return
     */
    @Query("select concat(u.name,'JK123') as name,upper(u.email) as email, e.idCard as idCard " +
            "from User u,UserExtend e " +
            "where u.id=e.userId and u.id=:id")
    List<UserSimpleDto> findByUserSimpleDtoId(Long id);
}
