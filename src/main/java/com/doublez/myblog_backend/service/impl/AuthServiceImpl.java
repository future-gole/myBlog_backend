package com.doublez.myblog_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doublez.myblog_backend.domain.ResultCode;
import com.doublez.myblog_backend.entity.User;
import com.doublez.myblog_backend.exception.ServiceException;
import com.doublez.myblog_backend.mapper.UserMapper;
import com.doublez.myblog_backend.service.IAuthService;

import com.doublez.myblog_backend.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserMapper userMapper;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String login(String username, String password) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));

        if (user == null) {
            throw new ServiceException(ResultCode.USER_NOT_EXITS);
        }

        if (!password.equals(user.getPasswordHash())) {
            throw new ServiceException(ResultCode.PASSWORD_ERROR);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        return JwtUtils.createToken(map,secret);
    }
}