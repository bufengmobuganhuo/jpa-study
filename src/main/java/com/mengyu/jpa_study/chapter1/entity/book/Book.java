package com.mengyu.jpa_study.chapter1.entity.book;

import lombok.Data;

import javax.persistence.*;

/**
 * @author yuzhang
 * @date 2020/12/4 下午5:19
 * TODO
 */
@Data
@Entity(name = "book")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "color", discriminatorType = DiscriminatorType.STRING)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
}
