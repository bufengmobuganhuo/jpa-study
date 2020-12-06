package com.mengyu.jpa_study.chapter1.entity.book;

import lombok.Data;

import javax.persistence.*;

/**
 * @author yuzhang
 * @date 2020/12/4 下午5:19
 * 基础类
 */
@Data
@Entity(name = "book")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
}
