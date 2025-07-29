package com.doublez.myblog_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doublez.myblog_backend.entity.User;
import com.doublez.myblog_backend.mapper.UserMapper;
import com.doublez.myblog_backend.service.IAuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements IAuthService {
//
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private  JwtUtil jwtUtil;
//
//    @Override
//    public String login(String username, String password) {
//        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
//
//        if (user == null) {
//            throw new RuntimeException("用户不存在");
//        }
//
//        if (!password.equals(user.getPasswordHash())) {
//            throw new RuntimeException("密码错误");
//        }
//
//        return jwtUtil.generateToken(user.getUsername());
//    }
//}