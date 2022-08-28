package com.longzai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.LoginUser;
import com.longzai.domain.entity.User;
import com.longzai.domain.vo.UserInfoVo;
import com.longzai.mapper.UserMapper;
import com.longzai.service.UserService;
import com.longzai.utils.BeanCopyUtils;
import com.longzai.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-08-26 16:23:26
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        // 获取用户id
        Long userId = SecurityUtils.getUserId();
        // 获取用户id 查询用户信息
        User user = getById(userId);
        // 封装成UserInfoVo
        UserInfoVo userInfoVo= BeanCopyUtils.copyBean(user,UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }
}


