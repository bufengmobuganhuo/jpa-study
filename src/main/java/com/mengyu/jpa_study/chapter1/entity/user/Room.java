package com.mengyu.jpa_study.chapter1.entity.user;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author yuzhang
 * @date 2020/12/5 下午4:43
 * TODO
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "room")
    private List<UserRoomRelation> userRoomRelations;
}
