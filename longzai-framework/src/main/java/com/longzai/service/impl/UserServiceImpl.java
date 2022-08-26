package com.longzai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longzai.domain.entity.User;
import com.longzai.mapper.UserMapper;
import com.longzai.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-08-26 16:23:26
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}


