package com.longzai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.Article;
import com.longzai.domain.entity.User;
import org.springframework.stereotype.Service;


public interface BolgLoginService  {
    ResponseResult login(User user);

    ResponseResult logout();
}
