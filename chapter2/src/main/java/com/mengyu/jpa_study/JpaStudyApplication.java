package com.mengyu.jpa_study;

import com.mengyu.jpa_study.chapter2.repository.CustomizedUserRepository;
import com.mengyu.jpa_study.chapter2.repository.impl.CustomerBaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryImplementationPostfix = "Impl", repositoryBaseClass = CustomerBaseRepository.class)
public class JpaStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaStudyApplication.class, args);
    }

}
