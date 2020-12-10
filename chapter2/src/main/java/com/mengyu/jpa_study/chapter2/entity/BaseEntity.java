package com.mengyu.jpa_study.chapter2.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

/**
 * @author yuzhang
 * @date 2020/12/9 下午7:21
 * TODO
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedBy
    private Integer createUserId;

    @CreatedDate
    private Instant createTime;

    @LastModifiedBy
    private Integer lastModifiedUserId;

    @LastModifiedDate
    private Instant lastModifiedtime;
}
