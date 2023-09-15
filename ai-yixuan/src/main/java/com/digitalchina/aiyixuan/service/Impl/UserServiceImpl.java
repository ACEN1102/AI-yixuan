package com.digitalchina.aiyixuan.service.Impl;

import com.digitalchina.aiyixuan.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitalchina.aiyixuan.model.User;
import com.digitalchina.aiyixuan.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
