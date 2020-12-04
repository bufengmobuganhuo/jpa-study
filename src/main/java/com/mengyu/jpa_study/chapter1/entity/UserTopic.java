package com.mengyu.jpa_study.chapter1.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuzhang
 * @date 2020/11/24 下午8:37
 * TODO
 */
@Data
@Entity
@Table(name = "user_topic")
@Access(AccessType.FIELD)
public class UserTopic {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = true, length = 200)
    private String title;

    @Basic
    @Column(name = "create_user_id", nullable = true)
    private Integer createUserId;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content", nullable = true, length = -1)
    @Lob
    private String content;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image", nullable = true)
    private byte[] image;

    @Basic
    @Column(name = "create_time", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date createTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "topic_type")
    private Type type;

    @Transient
    private String transientSimple;

    public String getTransientSimple() {
        return title + " auto:jack " + type;
    }

    public enum Type {
        EN("英文"), CN("中文");
        private final String des;

        Type(String des) {
            this.des = des;
        }
    }
}
