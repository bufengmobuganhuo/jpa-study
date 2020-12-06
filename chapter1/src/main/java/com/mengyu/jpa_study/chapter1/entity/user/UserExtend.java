package com.mengyu.jpa_study.chapter1.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author yuzhang
 * @date 2020/11/24 下午7:27
 * TODO
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserExtend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String idCard;

    private Integer age;

    private String studentNumber;
}
