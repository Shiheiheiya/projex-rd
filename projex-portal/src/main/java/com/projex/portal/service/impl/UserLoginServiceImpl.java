package com.projex.portal.service.impl;

import com.projex.portal.mapper.UserMapper;
import com.projex.portal.service.UserLoginService;
import com.projex.portal.vo.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResult getUserById(Integer userId) {
        return userMapper.getUser(userId);
    }
}
