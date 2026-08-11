package com.backend.blogfrontbackend.service.impl;

import com.backend.blogfrontbackend.entity.user.User;
import com.backend.blogfrontbackend.mapper.UserMapper;
import com.backend.blogfrontbackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public boolean updateUserByUserName(User user) {
        return userMapper.updateUserByUserName(user);
    }
}
