package com.mengyu.jpa_study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mengyu.jpa_study.chapter2.entity.User;
import com.mengyu.jpa_study.chapter2.entity.UserAddress;
import com.mengyu.jpa_study.chapter2.enums.SexEnum;
import com.mengyu.jpa_study.chapter2.repository.UserAddressRepositoryForQBE;
import com.mengyu.jpa_study.chapter2.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuzhang
 * @date 2020/12/6 下午12:07
 * TODO
 */
@SpringBootTest
public class QBETest {
    @Autowired
    private UserAddressRepositoryForQBE userAddressRepositoryForQBE;

    @Autowired
    private UserRepository userRepository;

    private Date now = new Date();

    @BeforeTestClass
    @Rollback(false)
    @Transactional
    public void init() {
        User user = User.builder()
                .name("jack")
                .email("126.163.com")
                .sex(SexEnum.BOY)
                .age(20)
                .createDate(Instant.now())
                .updateDate(now)
                .build();
        userAddressRepositoryForQBE.saveAll(Lists.newArrayList(UserAddress.builder().user(user).address("shanghai").build(),
                UserAddress.builder().user(user).address("beijing").build()));
    }

    @Test
    void test_QBE_From_User_Address() throws JsonProcessingException {
        User user = User.builder()
                .name("jack")
                .email("126.163.com")
                .sex(SexEnum.BOY)
                .age(20)
                .createDate(Instant.now())
                .updateDate(now)
                .build();
        userAddressRepositoryForQBE.saveAll(Lists.newArrayList(UserAddress.builder().user(user).address("shanghai").build(),
                UserAddress.builder().user(user).address("beijing").build()));

        User request = User.builder()
                .name("jack")
                .age(20)
                .email("126.163.com")
                .build();
        UserAddress address = UserAddress.builder().address("shang").user(request).build();
        ObjectMapper objectMapper = new ObjectMapper();
        System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address));

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("user.email", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("address", ExampleMatcher.GenericPropertyMatchers.startsWith());
        Page<UserAddress> page = userAddressRepositoryForQBE.findAll(Example.of(address, exampleMatcher), PageRequest.of(0, 2));
        System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(page));
    }

    @Test
    void test_jpaSpecificationExecutor() {
        User userQuery = User.builder()
                .name("jack")
                .email("126.163.com")
                .sex(SexEnum.BOY)
                .age(20)
                .address(Lists.newArrayList(UserAddress.builder().address("shanghai").build()))
                .build();
        // 时间范围
        Instant beginCreateDate = Instant.now().plus(-2, ChronoUnit.HOURS);
        Instant endCreateDate = Instant.now().plus(1, ChronoUnit.HOURS);
        // 利用Specification进行查询
        Page<User> users = userRepository.findAll(new Specification<User>() {
            /**
             *
             * @param root 代表了可以查询和操作的实体对象的根，如果将实体对象比喻成表名，
             *             那 root 里面就是这张表里面的字段，而这些字段只是 JPQL 的实体字段而已
             * @param query 代表一个 specific 的顶层查询对象，它包含着查询的各个部分，
             *              比如 select 、from、where、group by、order by 等
             * @param criteriaBuilder CriteriaBuilder 是用来构建 CritiaQuery 的构建器对象，
             *                        其实就相当于条件或者条件组合，并以 Predicate 的形式返回
             * @return
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> ps = new ArrayList<>();
                if (StringUtils.isNotBlank(userQuery.getName())) {
                    // 模仿like查询：name like '%'+#{name}+'%'
                    ps.add(criteriaBuilder.like(root.get("name"), "%" + userQuery.getName() + "%"));
                }
                if (userQuery.getSex() != null) {
                    // 精确查询：sex= #{name}
                    ps.add(criteriaBuilder.equal(root.get("sex"), userQuery.getSex()));
                }
                if (userQuery.getAge() != null) {
                    // 范围查询：age > #{age}
                    ps.add(criteriaBuilder.greaterThan(root.get("age"), userQuery.getAge()));
                }
                if (beginCreateDate != null && endCreateDate != null) {
                    // 范围查询：createDate between #{beginCreateDate} and #{endCreateDate}
                    ps.add(criteriaBuilder.between(root.get("createDate"),beginCreateDate,endCreateDate));
                }
                if (!ObjectUtils.isEmpty(userQuery.getAddress())){
                    // 关联查询：inner join user_address addresses1 on addresses1.address in ()
                    ps.add(criteriaBuilder.in(
                            root.join("address").get("address")
                    ).value(
                            userQuery.getAddress().stream().map(UserAddress::getAddress).collect(Collectors.toList())
                    ));
                }
                return query.where(ps.toArray(new Predicate[ps.size()])).getRestriction();
            }
        },PageRequest.of(0,2));
        System.err.println(users);
    }
}
