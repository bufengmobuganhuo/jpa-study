package com.mengyu.jpa_study.chapter1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author yuzhang
 * @date 2020/11/24 下午8:58
 * TODO
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo{
    private int age;

    @EmbeddedId
    private UserInfoId userInfoId;
}
