package com.mengyu.jpa_study.chapter1.repository.user;

import com.mengyu.jpa_study.chapter1.entity.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuzhang
 * @date 2020/11/24 下午9:01
 * TODO
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}
