package com.mengyu.jpa_study.chapter1.controller;

import com.mengyu.jpa_study.chapter1.entity.user.User;
import com.mengyu.jpa_study.chapter1.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuzhang
 * @date 2020/11/15 下午3:15
 * TODO
 */
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping(path = "user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User addNewUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping(path = "users")
    @ResponseBody
    public Page<User> getAllUsers(Pageable request){
        return userRepository.findAll(request);
    }

    @GetMapping(path = "/sort")
    public Page<User> getAllUserByPage(){
        return userRepository.findAll(
                PageRequest.of(1,20, Sort.by(Sort.Direction.ASC,"name"))
        );
    }


}
