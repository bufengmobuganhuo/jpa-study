package com.mengyu.jpa_study.chapter1.dto;

/**
 * @author yuzhang
 * @date 2020/11/20 下午5:04
 * 通过接口定义只返回部分字段
 */
public interface UserOnlyNameAndEmail {
    String getName();

    String getEmail();
}
