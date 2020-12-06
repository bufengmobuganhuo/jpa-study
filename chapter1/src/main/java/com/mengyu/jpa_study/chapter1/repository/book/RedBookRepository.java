package com.mengyu.jpa_study.chapter1.repository.book;

import com.mengyu.jpa_study.chapter1.entity.book.RedBook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuzhang
 * @date 2020/12/4 下午5:26
 * RedBook的Repository
 */
public interface RedBookRepository extends JpaRepository<RedBook, Long> {
}
