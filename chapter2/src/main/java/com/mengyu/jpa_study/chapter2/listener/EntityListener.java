package com.mengyu.jpa_study.chapter2.listener;

import com.mengyu.jpa_study.chapter2.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;

/**
 * @author yuzhang
 * @date 2020/12/10 下午5:25
 * TODO
 */
public class EntityListener {

    @PrePersist
    public void prePersist(Object user){
        System.err.println("prePersist:"+user.toString());
    }
}
