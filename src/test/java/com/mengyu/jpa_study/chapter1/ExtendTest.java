package com.mengyu.jpa_study.chapter1;

import com.mengyu.jpa_study.chapter1.entity.book.RedBook;
import com.mengyu.jpa_study.chapter1.repository.book.RedBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @author yuzhang
 * @date 2020/12/4 下午5:27
 * 多个实体之间的继承关系测试
 */
@SpringBootTest
public class ExtendTest {
    @Autowired
    private RedBookRepository redBookRepository;

    /**
     * 对应继承关系的第二种情况
     */
    @Test
    void test_single_table(){
        /*RedBook redBook = new RedBook();
        redBook.setTitle("redBook");
        redBook.setRedMark("redMark");
        redBook.setId(1L);
        redBookRepository.saveAndFlush(redBook);*/
        Optional<RedBook> res = redBookRepository.findById(8L);
        System.err.println(res.get());
    }

    @Test
    void test_join_table(){
        RedBook redBook = new RedBook();
        redBook.setTitle("redBook");
        redBook.setRedMark("redMark");
        redBook.setId(1L);
        redBookRepository.saveAndFlush(redBook);
        Optional<RedBook> res = redBookRepository.findById(1L);
        System.err.println(res.orElseGet(null));
    }
}
