package com.longzai.controller;

import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.User;
import com.longzai.enums.AppHttpCodeEnum;
import com.longzai.exception.SystemException;
import com.longzai.service.BolgLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BolgLoginController {
    @Autowired
    private BolgLoginService bolgLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            // 提示必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
      return   bolgLoginService.login(user);

    }
}
