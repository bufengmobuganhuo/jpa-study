package com.mengyu.chapter3.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

/**
 * @author yuzhang
 * @date 2020/12/9 下午7:21
 * TODO
 */
@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedBy
    private Integer createUserId;

    @CreatedDate
    private Instant createTime;

    @LastModifiedBy
    private Integer lastModifiedUserId;

    @LastModifiedDate
    private Instant lastModifiedtime;

    @Version
    private Integer version;
}
