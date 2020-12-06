package com.mengyu.jpa_study.chapter1.entity.user;

import lombok.*;

import javax.persistence.*;

/**
 * @author yuzhang
 * @date 2020/11/24 下午8:58
 * TODO
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer age;

    private String telephone;

    @OneToOne(orphanRemoval = true,fetch = FetchType.LAZY)
    // user_info中有一个"my_user_id"字段与user表中的id字段关联，但是他们之间不建立外键关系
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),name = "my_user_id")
    private User user;
}
