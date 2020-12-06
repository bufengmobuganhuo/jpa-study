package com.mengyu.jpa_study.chapter2.entity;

import com.mengyu.jpa_study.chapter2.enums.SexEnum;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/12/6 下午12:00
 * TODO
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "address")
@EqualsAndHashCode(exclude = "address")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SexEnum sex;

    private String email;

    private Integer age;

    private Instant createDate;

    private Date updateDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserAddress> address;
}
