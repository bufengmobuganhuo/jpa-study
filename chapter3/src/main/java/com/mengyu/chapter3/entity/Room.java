package com.mengyu.chapter3.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yuzhang
 * @date 2021/1/7 下午9:27
 * TODO
 */
@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Room extends BaseEntity{
    private String title;
}
