package com.longzai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.Category;
import com.longzai.domain.entity.User;
import com.longzai.mapper.BolgLoginMapper;
import com.longzai.mapper.CategoryMapper;
import com.longzai.service.BolgLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class BolgLoginServiceImpl implements BolgLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        authenticationManager.authenticate(authenticationToken);
        return null;
    }
}
