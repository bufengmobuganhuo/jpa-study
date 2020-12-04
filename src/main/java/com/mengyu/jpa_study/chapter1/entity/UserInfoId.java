package com.mengyu.jpa_study.chapter1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author yuzhang
 * @date 2020/11/24 下午8:59
 * TODO
 */
@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoId implements Serializable {
    private String name,telephone;
}
