package com.mengyu.chapter3.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mengyu.chapter3.service.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author yuzhang
 * @date 2021/1/7 下午9:15
 * TODO
 */
@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "userInfo")
// 指定名字为"getAllUserInfo"的实体关系图，指定关联关系属性"userInfo"可以被EntityGraph包含进去
@NamedEntityGraph(name = "getAllUserInfo", attributeNodes = @NamedAttributeNode(value = "userInfo"))
public class Address extends BaseEntity{
    private String city;

    @JsonBackReference //防止JSON死循环
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private UserInfo userInfo;
}
