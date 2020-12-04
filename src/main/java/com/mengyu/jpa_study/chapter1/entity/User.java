package com.mengyu.jpa_study.chapter1.entity;

import lombok.*;
import org.springframework.data.annotation.Version;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author yuzhang
 * @date 2020/11/15 下午3:11
 * TODO
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String sex;

    private String address;

    @Version
    private Long version;

}
