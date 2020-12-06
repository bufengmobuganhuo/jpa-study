package com.mengyu.jpa_study.chapter1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author yuzhang
 * @date 2020/11/24 下午7:32
 * TODO
 */
@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private String name, email, idCard;
}
