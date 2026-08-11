package com.backend.blogfrontbackend.controller;


import com.backend.blogfrontbackend.entity.RestBean;
import com.backend.blogfrontbackend.entity.user.User;
import com.backend.blogfrontbackend.entity.user.UserAccount;
import com.backend.blogfrontbackend.mapper.UserMapper;
import com.backend.blogfrontbackend.service.UserAccountService;
import com.backend.blogfrontbackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    UserMapper userMapper;

    @Resource
    UserAccountService service;


    @GetMapping("/me")
    public RestBean<UserAccount> me(@SessionAttribute("account") UserAccount user) {
        System.out.println(RestBean.success());
        return RestBean.success(user);
    }

    @GetMapping("/userInfo")
    public RestBean<User> userInfo(@SessionAttribute("account") UserAccount user) {
        User userInfo = userMapper.findByUsername(user.getUserAccountUserName());
        return RestBean.success(userInfo);
    }

    @PostMapping("/updateUserInfo")
    public RestBean<User> userInfo(
            @RequestParam("userAccountUserName") String userAccountUserName,
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("age") String age,
            @RequestParam("identity") String identity,
            @RequestParam("school") String school
    ) {
        User userInfo = new User();
        userInfo.setUserAccountUserName(userAccountUserName);
        userInfo.setName(name);
        userInfo.setGender(gender);
        userInfo.setIdentity(identity);
        userInfo.setSchool(school);
        userInfo.setAge(Integer.parseInt(age));
        Boolean flag = userService.updateUserByUserName(userInfo);
        return RestBean.success();
    }

}
