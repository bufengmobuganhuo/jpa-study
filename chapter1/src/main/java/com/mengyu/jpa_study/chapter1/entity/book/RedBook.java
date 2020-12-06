package com.mengyu.jpa_study.chapter1.entity.book;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @author yuzhang
 * @date 2020/12/4 下午5:24
 * 红色子对象
 */
@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class RedBook extends Book {
    private String redMark;
}
