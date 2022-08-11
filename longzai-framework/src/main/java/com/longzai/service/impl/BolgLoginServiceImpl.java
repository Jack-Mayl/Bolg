package com.longzai.service.impl;

import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.LoginUser;
import com.longzai.domain.entity.User;
import com.longzai.domain.vo.BlogUserLoginVo;
import com.longzai.domain.vo.UserInfoVo;
import com.longzai.service.BolgLoginService;
import com.longzai.utils.BeanCopyUtils;
import com.longzai.utils.JwtUtil;
import com.longzai.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BolgLoginServiceImpl implements BolgLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;


    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否认真通过
        if(Objects.isNull(authenticationToken)){
            throw new RuntimeException("用户名或者密码错误");
        }
        // 获取用户的token
        LoginUser loginUser= (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // 把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);
        // 把User转化成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(),UserInfoVo.class);
        BlogUserLoginVo userLoginVo=new BlogUserLoginVo(jwt,userInfoVo);
        // 把token和userinfo封装 返回
        return ResponseResult.okResult(userLoginVo);
    }

    @Override
    public ResponseResult logout() {
        // 获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 获取userid
        Long userId = loginUser.getUser().getId();
        // 删除redis中的用户信息
        redisCache.deleteObject("bloglogin:"+userId);
        return ResponseResult.okResult();
    }
}
