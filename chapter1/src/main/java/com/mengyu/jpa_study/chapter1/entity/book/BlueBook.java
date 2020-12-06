package com.mengyu.jpa_study.chapter1.entity.book;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * @author yuzhang
 * @date 2020/12/4 下午5:23
 * 子对象
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class BlueBook extends Book {
    // 子类特有字段
    private String blueMark;
}
