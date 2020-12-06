package com.mengyu.jpa_study.chapter1.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


/**
 * @author yuzhang
 * @date 2020/11/15 下午3:11
 * TODO
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;

    private String name;

    private String email;

    private String sex;

    @OneToMany(mappedBy = "user")
    private List<UserAddress> userAddresses;

    @OneToMany(mappedBy = "user")
    private List<UserRoomRelation> userRoomRelations;
}


