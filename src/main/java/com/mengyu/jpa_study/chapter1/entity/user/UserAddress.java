package com.mengyu.jpa_study.chapter1.entity.user;

import lombok.*;

import javax.persistence.*;

/**
 * @author yuzhang
 * @date 2020/12/5 下午4:14
 * TODO
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
