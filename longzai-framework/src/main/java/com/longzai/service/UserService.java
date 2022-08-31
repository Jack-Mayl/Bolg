package com.longzai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-08-26 16:23:25
 */
public interface UserService extends IService<User> {
     ResponseResult userInfo();

     ResponseResult updateUserInfo(User user);

     ResponseResult register(User user);
}


