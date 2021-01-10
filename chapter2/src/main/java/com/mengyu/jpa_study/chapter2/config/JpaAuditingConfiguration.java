package com.mengyu.jpa_study.chapter2.config;

import com.mengyu.jpa_study.chapter2.aware.MyAuditorAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author yuzhang
 * @date 2020/12/9 下午7:07
 * TODO
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {


    @Bean
    @ConditionalOnMissingBean(name = "myAuditorAware")
    MyAuditorAware myAuditorAware(){
        return new MyAuditorAware();
    }


}
