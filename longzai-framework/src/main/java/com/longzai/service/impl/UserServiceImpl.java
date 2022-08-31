package com.longzai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.LoginUser;
import com.longzai.domain.entity.User;
import com.longzai.domain.vo.UserInfoVo;
import com.longzai.enums.AppHttpCodeEnum;
import com.longzai.exception.SystemException;
import com.longzai.mapper.UserMapper;
import com.longzai.service.UserService;
import com.longzai.utils.BeanCopyUtils;
import com.longzai.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-08-26 16:23:26
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        // 获取用户id
        String userId = SecurityUtils.getUserId();
        // 获取用户id 查询用户信息
        User user = getById(userId);
        // 封装成UserInfoVo
        UserInfoVo userInfoVo= BeanCopyUtils.copyBean(user,UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        LambdaUpdateWrapper<User> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(User::getId,user.getId())
                .set(User::getAvatar,user.getAvatar())
                .set(User::getEmail,user.getEmail())
                .set(User::getId,user.getId())
                .set(User::getNickName,user.getNickName())
                .set(User::getSex,user.getSex());
        boolean update = update(lambdaUpdateWrapper);
        if (!update){
            throw new SystemException(AppHttpCodeEnum.LOGIN_FAILED);
        }

        System.out.println(update);
        return ResponseResult.okResult();
    }
        @Autowired
        private PasswordEncoder passwordEncoder;
    @Override
    public ResponseResult register(User user) {
        //对数据进行非空判断
        //对数据进行是否存在的判断
            // 对用户名进行重复的判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
            // 对昵称进行重复的判断
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
            //对邮箱进行重复的判断
        if(emailExist(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }

        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //存入数据库
        save(user);
        return ResponseResult.okResult();
    }

    private boolean emailExist(String email) {
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getEmail,email);
        return count(lambdaQueryWrapper)>0;
    }


    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getNickName,nickName);
        return count(lambdaQueryWrapper)>0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,userName);
        return count(lambdaQueryWrapper)>0;
    }
}


