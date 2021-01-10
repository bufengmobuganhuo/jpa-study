package com.mengyu.chapter3.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;


/**
 * @author yuzhang
 * @date 2020/11/24 下午8:58
 * TODO
 */
@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "addressList")
@NamedEntityGraphs(value = {
        // 名字叫"addressGraph"的的实体关系图
        @NamedEntityGraph(name = "addressGraph", attributeNodes = @NamedAttributeNode(value = "addressList")),
        // 名字叫"rooms"的的实体关系图
        @NamedEntityGraph(name = "rooms", attributeNodes = @NamedAttributeNode(value = "rooms"))
})
public class UserInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer age;

    private String telephone;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Address> addressList;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Room> rooms;
}
