package com.mengyu.jpa_study.chapter1.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuzhang
 * @date 2020/12/5 下午4:51
 * TODO
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoomRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createTime, updateTime;

    @ManyToOne
    private Room room;

    @ManyToOne
    private User user;
}
