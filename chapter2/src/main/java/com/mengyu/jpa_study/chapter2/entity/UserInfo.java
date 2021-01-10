package com.mengyu.jpa_study.chapter2.entity;

import lombok.*;

import javax.persistence.*;



/**
 * @author yuzhang
 * @date 2020/11/24 下午8:58
 * TODO
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class UserInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer age;

    private String telephone;
}
